package com.sample.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 */
public final class PasswordUtils {

    public static final String ALLOW_SYMBOLS = "ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789";
    public static final int DEFAULT_PASSWORD_LENGTH = 8;

    private PasswordUtils() {
    }

    public static String hashPassword(final String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    public static boolean checkPassword(final String plain, final String encoded) {
        return BCrypt.checkpw(plain, encoded);
    }

    public static String generatePassword() {
        return RandomStringUtils.random(DEFAULT_PASSWORD_LENGTH, ALLOW_SYMBOLS);
    }
}
