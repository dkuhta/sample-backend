package com.sample.accounts;

import com.softteco.toolset.bl.SkipAutoFill;

import java.io.Serializable;
import java.util.Set;

/**
 * Created on 1.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AccountDto implements Serializable {

    private static final long serialVersionUID = -3740159350872566554L;

    public String email;
    public String name;
    @SkipAutoFill
    public Set<String> roles;

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
}
