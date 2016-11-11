package com.sample.accounts;

import com.google.inject.ImplementedBy;
import com.sample.accounts.password.PasswordResetDto;
import com.sample.accounts.password.PasswordUpdateDto;
import com.sample.accounts.login.LoginDto;
import com.sample.accounts.login.LogoutDto;
import com.sample.accounts.singup.SingupDto;
import com.softteco.toolset.jpa.DataNotFoundException;
import com.softteco.toolset.restlet.AuthorizationException;

/**
 * Created on 7.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(AccountServiceBean.class)
public interface AccountService {

    AccountDto singup(SingupDto dto);

    AccountDto login(LoginDto dto) throws AuthorizationException;

    void logout(LogoutDto dto);

    AccountDto getAccount();

    AccountDto updateAccount(AccountUpdateDto dto);

    void resetPassword(PasswordResetDto dto) throws DataNotFoundException;

    void updatePassword(PasswordUpdateDto dto);
}
