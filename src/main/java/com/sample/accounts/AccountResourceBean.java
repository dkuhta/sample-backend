package com.sample.accounts;

import com.google.inject.Inject;
import com.softteco.toolset.restlet.AbstractResource;
import com.softteco.toolset.restlet.UserSession;

/**
 * Created on 9.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AccountResourceBean extends AbstractResource<UserSession> implements AccountResource {

    @Inject
    private AccountService accountService;

    public AccountDto getProfile() {
        return accountService.getAccount();
    }

    @Override
    public AccountDto update(final AccountUpdateDto dto) {
        return accountService.updateAccount(dto);
    }
}
