package com.mmf.rest.util;

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
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.authentication.encoding.PasswordEncoder;

import javax.inject.Named;
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

    private PasswordEncoder passwordEncoder;
    private SaltSource saltSource;

    public PasswordGenerator() {
    }

    public PasswordGenerator(PasswordEncoder passwordEncoder, SaltSource saltSource) {
        this.passwordEncoder = passwordEncoder;
        this.saltSource = saltSource;
    }

    public void hashPassword(User user) throws BusinessServiceException {
        byte[] bSalt;
        String sSalt = null;
        String sDigest = null;

        try {
            bSalt = getSalt();
            sSalt = byteToBase64(bSalt);
            user.setPasswordSalt(sSalt);
            user.setPassword(passwordEncoder.encodePassword(user.getPassword(), saltSource.getSalt(user)));
//            sDigest = getHash(ITERATION_NUMBER, user.getLogin(), bSalt, PASSWORD_FORMAT);
//            user.setPassword(sDigest);
            user.setPasswordFormat(PASSWORD_FORMAT);
        } catch (NoSuchAlgorithmException ignored) {
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

    public PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    public SaltSource getSaltSource() {
        return saltSource;
    }

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public void setSaltSource(SaltSource saltSource) {
        this.saltSource = saltSource;
    }
}
