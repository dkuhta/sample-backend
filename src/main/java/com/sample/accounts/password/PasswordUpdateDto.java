package com.sample.accounts.password;

import java.io.Serializable;

/**
 * Created on 9.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class PasswordUpdateDto implements Serializable {

    private static final long serialVersionUID = 9066804516747922737L;

    public String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
