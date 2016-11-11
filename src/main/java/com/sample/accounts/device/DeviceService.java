package com.sample.accounts.device;

import com.google.inject.ImplementedBy;
import com.sample.accounts.AccountEntity;
import com.sample.auth.LogoutDto;

/**
 * Created on 11.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
@ImplementedBy(DeviceServiceBean.class)
public interface DeviceService {

    void login(DeviceDto deviceDto, AccountEntity accountE);

    void logout(DeviceLogoutDto deviceDto);
}
