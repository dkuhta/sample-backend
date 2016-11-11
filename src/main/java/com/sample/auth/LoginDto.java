package com.sample.auth;

import com.sample.accounts.device.DeviceDto;

import java.io.Serializable;

/**
 * Created on 1.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class LoginDto implements Serializable {

    private static final long serialVersionUID = 976450295147703248L;

    public String email;
    public String password;

    public DeviceDto device;

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public DeviceDto getDevice() {
        return device;
    }

    public void setDevice(final DeviceDto device) {
        this.device = device;
    }
}
