package com.sample.auth;

import java.io.Serializable;

/**
 * Created on 1.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AuthDto implements Serializable {

    private static final long serialVersionUID = 976450295147703248L;

    public String email;
    public String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
