package com.sample.accounts;

import com.google.inject.ImplementedBy;
import com.sample.singup.SingupDto;

/**
 * Created on 7.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(AccountServiceBean.class)
public interface AccountService {

    AccountDto singup(SingupDto dto);
}
