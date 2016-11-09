package com.sample.accounts.password;

import java.io.Serializable;

/**
 * Created on 9.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class PasswordResetDto implements Serializable {

    private static final long serialVersionUID = -3750418413904065679L;

    public String email;
    public String lang;

    public String getLang() {
        return lang;
    }

    public void setLang(final String lang) {
        this.lang = lang;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
