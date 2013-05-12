package com.mmf;

import com.mmf.business.BusinessServiceException;
import com.mmf.business.UserService;
import com.mmf.business.domain.User;
import com.mmf.db.dao.DataAccessException;
import com.mmf.db.dao.LecturerDao;
import com.mmf.db.dao.StudentDao;
import com.mmf.db.dao.UserDao;
import com.mmf.db.dao.jpa.UserDaoImpl;
import com.mmf.db.model.LecturerEntity;
import com.mmf.db.model.StudentEntity;
import com.mmf.db.model.UserEntity;
import com.sun.jersey.api.spring.Autowire;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.List;

/**
 * User: svetlana.voyteh
 * Date: 12.05.13
 */
public class PasswordGenerator {
    private static final int SALT_SIZE = 8;
    private static final int ITERATION_NUMBER = 1000;
    private static final String ALGORITHM = "SHA1PRNG";
    private static final String PASSWORD_FORMAT = "SHA-1";
    private static final String CHARSET = "UTF-8";

    public void updatePasswords(UserService service) throws BusinessServiceException {
        List<User> users = service.list();
        byte[] bSalt;
        String sSalt = null;
        String sDigest = null;
        for (User user : users) {

            try {
                bSalt = getSalt();
                sSalt = byteToBase64(bSalt);
                sDigest = getHash(ITERATION_NUMBER, user.getLogin(), bSalt, PASSWORD_FORMAT);
                user.setPassword(sDigest);
                user.setPasswordSalt(sSalt);
                user.setPasswordFormat(PASSWORD_FORMAT);
                service.update(user);
            } catch (NoSuchAlgorithmException ignored) {
            } catch (UnsupportedEncodingException ignored) {
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
}
