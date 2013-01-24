package com.mmf.db.dao.jpa;

import com.mmf.db.dao.UserDao;
import com.mmf.db.model.DepartmentEntity;
import com.mmf.db.model.GroupEntity;
import com.mmf.db.model.UserEntity;
import org.apache.commons.codec.binary.Base64;

import javax.inject.Named;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author svetlana.voyteh
 * @date: 3/12/12
 */
@Named
public class UserDaoImpl extends GenericJpaDao<Long, UserEntity> implements UserDao{

    private static final String SQL_GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM user LEFT JOIN group ON user.ID_Group = group.ID WHERE user.Login = ? ;";
    private static final String SQL_GET_ALL_USERS = "SELECT * FROM user;";
    private static final String SQL_UPDATE_USER_BY_ID = "UPDATE user SET Password = ?, PasswordSalt = ?, PasswordFormat = ? WHERE ID = ?;";
    private static final String SQL_GET_ALL_LECTURERS = "SELECT * FROM user LEFT JOIN department ON user.ID_Department = department.ID WHERE (user.ID_UserRole = 2 AND user.ID_Department > 0) ;";

    private static final int SALT_SIZE = 8;
    private static final int ITERATION_NUMBER = 1000;
    private static final String ALGORITHM = "SHA1PRNG";
    private static final String PASSWORD_FORMAT = "SHA-1";
    private static final String CHARSET = "UTF-8";

    public UserEntity getUser(String login, String pass) {
        UserEntity user = null;
        Connection conn = null;
        try {
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(URL + DB_NAME, getProperties());
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_USER_BY_LOGIN_AND_PASSWORD);
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                user = new UserEntity();

                GroupEntity group = new GroupEntity();
                group.setNumber(resultSet.getInt("Number"));
                group.setSubGroup(resultSet.getString("SubGroup"));

                user.setGroup(group);
                user.setLogin(login);
                user.setName(resultSet.getString("Name"));
                user.setSurname(resultSet.getString("Surname"));
                user.setPassword(resultSet.getString("Password"));
                user.setPasswordSalt(resultSet.getString("PasswordSalt"));
                user.setPasswordFormat(resultSet.getString("PasswordFormat"));
                user.setDateOfEntrance(resultSet.getDate("Date_Of_Entrance"));

                String sDigest = getHash(ITERATION_NUMBER, pass, base64ToByte(user.getPasswordSalt()), user.getPasswordFormat());
                if (!user.getPassword().equals(sDigest)){
                    user = null;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return user;
    }
    
    public List<UserEntity> getAllLecturers(){
        List<UserEntity> lecturers = new ArrayList<UserEntity>();
        UserEntity lecturer;
        Connection conn = null;
        try {
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(URL + DB_NAME, getProperties());
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_LECTURERS);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                lecturer = new UserEntity();

                DepartmentEntity department = new DepartmentEntity();
                department.setName(resultSet.getString("Department.Name"));
                department.setId(resultSet.getLong("Department.ID"));

                lecturer.setDepartment(department);
                lecturer.setName(resultSet.getString("User.Name"));
                lecturer.setId(resultSet.getLong("User.ID"));
                lecturers.add(lecturer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lecturers;
    }

    public List<UserEntity> getAllUsersForHashPassword(){
        List<UserEntity> users = new ArrayList<UserEntity>();
        Connection conn = null;
        try {
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(URL + DB_NAME, getProperties());
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_GET_ALL_USERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                UserEntity user = new UserEntity();
                user.setId(resultSet.getLong("ID"));
                user.setPassword(resultSet.getString("Password"));
                users.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public void updateAllUsersForHashPassword(List<UserEntity> users){
        Connection conn = null;
        try {
            Class.forName(DRIVER).newInstance();
            conn = DriverManager.getConnection(URL + DB_NAME, getProperties());
            for (UserEntity user : users){
                PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE_USER_BY_ID);
                preparedStatement.setString(1, user.getPassword());
                preparedStatement.setString(2, user.getPasswordSalt());
                preparedStatement.setString(3, user.getPasswordFormat());
                preparedStatement.setLong(4, user.getId());
                preparedStatement.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance(ALGORITHM);
        byte[] bSalt = new byte[SALT_SIZE];
        random.nextBytes(bSalt);
        return bSalt;
    }

    private static String getHash(int iterationNb, String password, byte[] salt, String passwordFormat)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance(passwordFormat);
        digest.reset();
        digest.update(salt);
        byte[] input = digest.digest(password.getBytes(CHARSET));
        for (int i = 0; i < iterationNb; i++) {
            digest.reset();
            input = digest.digest(input);
        }
        return byteToBase64(input);
    }

    private static String byteToBase64(byte[] data) {
        return new String(Base64.encodeBase64(data));
    }

    public byte[] base64ToByte(String data) throws IOException {
        return Base64.decodeBase64(data.getBytes());
    }

    @Override
    protected Class<UserEntity> getEntityClass() {
        return UserEntity.class;
    }
}
