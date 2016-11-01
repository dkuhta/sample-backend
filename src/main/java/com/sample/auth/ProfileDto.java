package com.sample.auth;

import java.io.Serializable;

/**
 * Created on 1.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class ProfileDto implements Serializable {

    private static final long serialVersionUID = -3740159350872566554L;

    private String username;

    /**
     * Gets username.
     *
     * @return Value of username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets new username.
     *
     * @param username New value of username.
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
