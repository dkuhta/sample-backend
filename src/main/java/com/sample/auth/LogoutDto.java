package com.sample.auth;

import com.sample.accounts.device.DeviceLogoutDto;

import java.io.Serializable;

/**
 * Created on 8.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class LogoutDto implements Serializable {

    private static final long serialVersionUID = 7352607440100389214L;

    public DeviceLogoutDto device;

    public DeviceLogoutDto getDevice() {
        return device;
    }

    public void setDevice(final DeviceLogoutDto device) {
        this.device = device;
    }
}
