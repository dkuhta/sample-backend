package com.sample.accounts;

import com.google.inject.Inject;
import com.google.inject.persist.Transactional;
import com.sample.accounts.device.DeviceDao;
import com.sample.accounts.device.DeviceEntity;
import com.sample.accounts.device.DeviceStatus;
import com.sample.accounts.password.PasswordResetDto;
import com.sample.accounts.password.PasswordUpdateDto;
import com.sample.accounts.roles.Role;
import com.sample.accounts.roles.RoleEntity;
import com.sample.accounts.roles.RoleService;
import com.sample.auth.AuthDto;
import com.sample.auth.LogoutDto;
import com.sample.mail.MailNotificationService;
import com.sample.singup.SingupDto;
import com.sample.util.PasswordUtils;
import com.softteco.toolset.jpa.DataNotFoundException;
import com.softteco.toolset.restlet.AuthorizationException;
import com.softteco.toolset.restlet.AuthorizationStatus;
import com.softteco.toolset.restlet.UserSession;
import com.softteco.toolset.security.AssertAuthorizedUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityExistsException;
import java.text.MessageFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created on 7.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AccountServiceBean implements AccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceBean.class);

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

    @Inject
    private DeviceDao deviceDao;

    @Transactional
    @Override
    public AccountDto singup(final SingupDto dto) {
        if (accountDao.findByEmail(dto.email) != null) {
            throw new EntityExistsException("Account with this email already exists!");
        }

        userSession.setLang(dto.lang);

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

        //TODO
        /*DeviceEntity deviceE = deviceDao.findByDevice(dto.device.deviceId, dto.device.type);
        if (deviceE == null) {
            deviceEntity = deviceDtoAssembler.assemble(personData.device);
            deviceEntity.setPerson(personEntity);
            deviceDao.persist(deviceEntity);
        } else {
            deviceDtoAssembler.assemble(personData.device, deviceE);
            deviceE.setPerson(personEntity);
            deviceDao.merge(deviceE);
        }*/

        AccountDto accountDto = accountDtoAssembler.assemble(accountE);

        userSession.setUsername(accountDto.email);
        userSession.setRoles(accountDto.roles);

        return accountDto;
    }

    @AssertAuthorizedUser
    @Transactional
    @Override
    public void logout(final LogoutDto dto) {
        final DeviceEntity deviceE = deviceDao.findByDeviceAndAccountEamil(dto.device.deviceId, dto.device.type, userSession.getUsername());
        if (deviceE == null) {
            LOGGER.error(MessageFormat.format("Device with id {0} for user with email {1} not found", dto.device.deviceId, userSession.getUsername()));
            return;
        }
        deviceE.setStatus(DeviceStatus.LOGGED_OUT);
        deviceDao.merge(deviceE);
    }

    @AssertAuthorizedUser
    @Override
    public AccountDto getAccount() {
        return accountDtoAssembler.assemble(getSessionAccount());
    }

    private AccountEntity getSessionAccount() {
        return accountDao.findByEmail(userSession.getUsername());
    }

    @AssertAuthorizedUser
    @Transactional
    @Override
    public AccountDto updateAccount(final AccountUpdateDto dto) {
        AccountEntity accountE = getSessionAccount();

        //TODO update account

        return accountDtoAssembler.assemble(accountE);
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
    public void updatePassword(final PasswordUpdateDto dto) {
        AccountEntity accountE = getSessionAccount();
        accountE.setPassword(PasswordUtils.hashPassword(dto.password));
        accountDao.merge(accountE);
    }
}
