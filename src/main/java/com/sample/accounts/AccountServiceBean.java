package com.sample.accounts;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.sample.accounts.password.PasswordResetDto;
import com.sample.accounts.password.PasswordUpdateDto;
import com.sample.accounts.roles.Role;
import com.sample.accounts.roles.RoleEntity;
import com.sample.accounts.roles.RoleService;
import com.sample.mail.MailNotificationService;
import com.sample.singup.SingupDto;
import com.sample.util.PasswordUtils;
import com.softteco.toolset.jpa.DataNotFoundException;
import com.softteco.toolset.restlet.AuthorizationException;
import com.softteco.toolset.restlet.AuthorizationStatus;
import com.softteco.toolset.restlet.UserSession;
import com.softteco.toolset.security.AssertAuthorizedUser;

import javax.persistence.EntityExistsException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 7.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AccountServiceBean implements AccountService {

    @Inject
    private AccountDao accountDao;

    @Inject
    private RoleService roleService;

    @Inject
    private AccountDtoAssembler accountDtoAssembler;

    @Inject
    private MailNotificationService mailNotificationService;

    @Inject
    private UserSession userSession;

    @Transactional
    @Override
    public AccountDto singup(final SingupDto dto) {
        if (accountDao.findByEmail(dto.email) != null) {
            throw new EntityExistsException("Account with this email already exists!");
        }

        AccountEntity personE = new AccountEntity();
        personE.setEmail(dto.email);
        personE.setName(dto.name);
        personE.setPassword(PasswordUtils.hashPassword(dto.password));
        personE.setStatus(AccountStatus.ACTIVE);
        Set<RoleEntity> roles = new HashSet<>();
        roles.add(roleService.getRole(Role.USER));
        personE.setRoles(roles);
        accountDao.persist(personE);
        accountDao.flush();

        mailNotificationService.sendWelcome(dto.email);

        return accountDtoAssembler.assemble(personE);
    }

    @AssertAuthorizedUser
    @Override
    public AccountDto getAccount() throws AuthorizationException {
        return accountDtoAssembler.assemble(getSessionAccount());
    }

    private AccountEntity getSessionAccount() throws AuthorizationException {
        AccountEntity accountE = accountDao.findByEmail(userSession.getUsername());

        if (AccountStatus.DISABLED.equals(accountE.getStatus())) {
            throw new AuthorizationException(AuthorizationStatus.DISABLED);
        }

        return accountE;
    }

    @AssertAuthorizedUser
    @Transactional
    @Override
    public AccountDto updateAccount(final AccountDto dto) {
        return null;
    }

    @Transactional
    @Override
    public void resetPassword(final PasswordResetDto dto) throws DataNotFoundException {
        AccountEntity accountE = accountDao.findByEmail(dto.email);

        if (accountE == null) {
            throw new DataNotFoundException("Account was not found.");
        }

        userSession.setLang(dto.lang);

        String newPassword = PasswordUtils.generatePassword();
        accountE.setPassword(PasswordUtils.hashPassword(newPassword));
        accountDao.merge(accountE);
        accountDao.flush();

        mailNotificationService.sendResetPassword(dto.email, newPassword);
    }

    @AssertAuthorizedUser
    @Transactional
    @Override
    public void updatePassword(final PasswordUpdateDto dto) throws AuthorizationException {
        AccountEntity accountE = getSessionAccount();
        accountE.setPassword(PasswordUtils.hashPassword(dto.password));
        accountDao.merge(accountE);
    }
}
