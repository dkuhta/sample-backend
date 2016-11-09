package com.sample.accounts.device;

import com.wordnik.swagger.annotations.ApiParam;

import java.io.Serializable;

/**
 * Created on 9.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class DeviceLogoutDto implements Serializable {

    private static final long serialVersionUID = -5721262834056497313L;

    @ApiParam(allowableValues = "IOS, ANDROID")
    public DeviceType type;
    public String deviceId;

    public DeviceType getType() {
        return type;
    }

    public void setType(final DeviceType type) {
        this.type = type;
    }

    @ApiParam(allowableValues = "IOS, ANDROID")
    public String getDeviceId() {
        return deviceId;
    }

    @ApiParam(allowableValues = "IOS, ANDROID")
    public void setDeviceId(final String deviceId) {
        this.deviceId = deviceId;
    }
}
