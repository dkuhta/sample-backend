package com.sample.auth;

import com.google.inject.ImplementedBy;
import com.sample.accounts.AccountDto;
import com.softteco.toolset.restlet.AuthorizationException;

/**
 * Created on 1.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(AuthServiceBean.class)
public interface AuthService {

    AccountDto authorize(AuthDto dto) throws AuthorizationException;

    void logout(LogoutDto dto);
}
