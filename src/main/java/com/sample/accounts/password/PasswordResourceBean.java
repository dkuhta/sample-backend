package com.sample.accounts.password;

import com.google.inject.Inject;
import com.sample.accounts.AccountService;
import com.softteco.toolset.jpa.DataNotFoundException;
import com.softteco.toolset.restlet.AbstractResource;
import com.softteco.toolset.restlet.AuthorizationException;
import com.softteco.toolset.restlet.UserSession;

/**
 * Created on 9.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class PasswordResourceBean extends AbstractResource<UserSession> implements PasswordResource {

    @Inject
    private AccountService accountService;

    @Override
    public void reset(final PasswordResetDto dto) throws DataNotFoundException {
        accountService.resetPassword(dto);
    }

    @Override
    public void update(final PasswordUpdateDto dto) {
        accountService.updatePassword(dto);
    }
}
