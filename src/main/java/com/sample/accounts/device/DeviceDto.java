package com.sample.accounts.device;

/**
 * Created by dkuhta on 27.4.15.
 */
public class DeviceDto extends DeviceLogoutDto {

    private static final long serialVersionUID = -3901255077145293435L;

    public String notificationToken;
    public String lang;

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(final String notificationToken) {
        this.notificationToken = notificationToken;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(final String lang) {
        this.lang = lang;
    }
}
