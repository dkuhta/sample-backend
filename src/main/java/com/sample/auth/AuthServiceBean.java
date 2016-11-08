package com.sample.auth;

import com.google.inject.Inject;
import com.sample.accounts.AccountDao;
import com.sample.accounts.AccountDtoAssembler;
import com.sample.accounts.AccountEntity;
import com.sample.accounts.AccountService;
import com.sample.accounts.AccountDto;
import com.sample.accounts.AccountStatus;
import com.sample.util.PasswordUtils;
import com.softteco.toolset.restlet.AuthorizationException;
import com.softteco.toolset.restlet.AuthorizationStatus;
import com.softteco.toolset.restlet.UserSession;
import com.softteco.toolset.security.AssertAuthorizedUser;

/**
 * Created on 1.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AuthServiceBean implements AuthService {

    @Inject
    private UserSession userSession;

    @Inject
    private AccountService personService;

    @Inject
    private AccountDao accountDao;

    @Inject
    private AccountDtoAssembler accountDtoAssembler;

    @AssertAuthorizedUser
    @Override
    public AccountDto getCurrent() throws AuthorizationException {
        AccountEntity accountE = accountDao.findByEmail(userSession.getUsername());

        if (AccountStatus.DISABLED.equals(accountE.getStatus())) {
            throw new AuthorizationException(AuthorizationStatus.DISABLED);
        }

        return accountDtoAssembler.assemble(accountE);
    }

    @Override
    public AccountDto authorize(final AuthDto dto) throws AuthorizationException {
        AccountEntity accountE = accountDao.findByEmail(dto.email);

        if (accountE == null) {
            throw new AuthorizationException(AuthorizationStatus.INCORECT_LOGIN_OR_PASSWORD);
        }

        if (AccountStatus.DISABLED.equals(accountE.getStatus())) {
            throw new AuthorizationException(AuthorizationStatus.DISABLED);
        }

        if (!PasswordUtils.checkPassword(dto.password, accountE.getPassword())) {
            throw new AuthorizationException(AuthorizationStatus.INCORECT_LOGIN_OR_PASSWORD);
        }

        AccountDto accountDto = accountDtoAssembler.assemble(accountE);

        userSession.setUsername(accountDto.email);
        userSession.setRoles(accountDto.roles);

        return accountDto;
    }

    @AssertAuthorizedUser
    @Override
    public void logout(final LogoutDto dto) {

    }
}
