package com.sample.accounts.login;

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
public class LoginResourceBean extends AbstractResource<UserSession> implements LoginResource {

    @Inject
    private AccountService accountService;

    public AccountDto login(final LoginDto dto) throws AuthorizationException {
        return accountService.login(dto);
    }

    public void logout(final LogoutDto dto) {
        accountService.logout(dto);
        getHttpServletRequest().getSession().invalidate();
    }
}
