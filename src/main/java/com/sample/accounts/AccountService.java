package com.sample.accounts;

import com.google.inject.ImplementedBy;
import com.sample.accounts.password.PasswordResetDto;
import com.sample.accounts.password.PasswordUpdateDto;
import com.sample.singup.SingupDto;
import com.softteco.toolset.jpa.DataNotFoundException;

/**
 * Created on 7.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(AccountServiceBean.class)
public interface AccountService {

    AccountDto singup(SingupDto dto);

    AccountDto getAccount();

    AccountDto updateAccount(AccountUpdateDto dto);

    void resetPassword(PasswordResetDto dto) throws DataNotFoundException;

    void updatePassword(PasswordUpdateDto dto);
}
