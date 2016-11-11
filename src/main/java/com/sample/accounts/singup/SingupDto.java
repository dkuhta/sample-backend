package com.sample.accounts.singup;

import java.io.Serializable;

/**
 * Created on 7.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class SingupDto implements Serializable {

    private static final long serialVersionUID = 821034105396398270L;

    public String email;
    public String name;
    public String password;
    public String lang;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(final String lang) {
        this.lang = lang;
    }
}
