package com.sample.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 */
public final class PasswordUtils {

    private PasswordUtils() {
    }

    public static String hashPassword(final String plain) {
        return BCrypt.hashpw(plain, BCrypt.gensalt());
    }

    public static boolean checkPassword(final String plain, final String encoded) {
        return BCrypt.checkpw(plain, encoded);
    }
}
