package com.sample.auth;

import com.google.inject.Inject;
import com.sample.accounts.AccountDto;
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
    private AuthService authService;

    public AccountDto getProfile() throws AuthorizationException {
        return authService.getCurrent();
    }

    public AccountDto login(final AuthDto dto) throws AuthorizationException {
        return authService.authorize(dto);
    }

    public void logout(final LogoutDto dto) {
        authService.logout(dto);
        getHttpServletRequest().getSession().invalidate();
    }
}
