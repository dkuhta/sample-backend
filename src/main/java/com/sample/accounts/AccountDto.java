package com.sample.accounts;

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
    public Set<String> roles;
}
