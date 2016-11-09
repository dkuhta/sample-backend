package com.sample.accounts;

import com.sample.accounts.device.DeviceDto;

import java.io.Serializable;

/**
 * Created on 9.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class AccountUpdateDto implements Serializable {

    private static final long serialVersionUID = -3270590146501840146L;

    public DeviceDto device;

    public DeviceDto getDevice() {
        return device;
    }

    public void setDevice(final DeviceDto device) {
        this.device = device;
    }
}
