package com.sample.auth;

import com.google.inject.Inject;
import com.sample.accounts.AccountDto;
import com.sample.accounts.AccountService;
import com.softteco.toolset.restlet.AbstractResource;
import com.softteco.toolset.restlet.AuthorizationException;
import com.softteco.toolset.restlet.UserSession;

/**
 * Created on 1.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AuthResourceBean extends AbstractResource<UserSession> implements AuthResource {

    @Inject
    private AccountService accountService;

    public AccountDto login(final AuthDto dto) throws AuthorizationException {
        return accountService.authorize(dto);
    }

    public void logout(final LogoutDto dto) {
        accountService.logout(dto);
        getHttpServletRequest().getSession().invalidate();
    }
}
