package com.sample.accounts;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.sample.accounts.roles.Role;
import com.sample.accounts.roles.RoleEntity;
import com.sample.accounts.roles.RoleService;
import com.sample.mail.MailNotificationService;
import com.sample.singup.SingupDto;
import com.sample.util.PasswordUtils;

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

        mailNotificationService.sendWelcomeEmail(dto.email);

        return accountDtoAssembler.assemble(personE);
    }
}
