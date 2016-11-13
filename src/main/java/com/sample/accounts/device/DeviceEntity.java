package com.sample.accounts.device;

import com.sample.accounts.AccountEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by dkuhta on 27.4.15.
 */
@Entity
@Table(name = "DEVICE", indexes = {
        @Index(name = "dev_id_index", columnList = "DEVICE_ID, TYPE")
})
@NamedQueries({
        @NamedQuery(name = DeviceEntity.FIND_ALL_BY_ACCOUNT_ID,
                query = "select d from DeviceEntity d where d.account.id = :personId"),
        @NamedQuery(name = DeviceEntity.FIND_BY_DEVICE,
                query = "select d from DeviceEntity d where d.deviceId = :deviceId and d.type = :type"),
        @NamedQuery(name = DeviceEntity.FIND_BY_DEVICE_AND_ACCOUNT_EMAIL,
                query = "select d from DeviceEntity d where d.deviceId = :deviceId and d.account.email = :email and d.type = :type")
})
public class DeviceEntity implements Serializable {

    private static final long serialVersionUID = 804698866511567133L;

    public static final String FIND_ALL_BY_ACCOUNT_ID = "DeviceEntity.findAllByAccountId";
    public static final String FIND_BY_DEVICE = "DeviceEntity.findByDevice";
    public static final String FIND_BY_DEVICE_AND_ACCOUNT_EMAIL = "DeviceEntity.findByDeviceAndAccountEamil";

    @Id
    @GeneratedValue(generator = "DeviceEntity")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", columnDefinition = "VARCHAR(60) NOT NULL")
    private DeviceType type;

    @Column(name = "DEVICE_ID")
    private String deviceId;

    @Column(name = "NOTIFICATION_TOKEN")
    private String notificationToken;

    @Column(name = "LANG", columnDefinition = "VARCHAR(10) NOT NULL")
    private String lang;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(60) NOT NULL")
    private DeviceStatus status;

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", columnDefinition = "BIGINT(20) NOT NULL")
    private AccountEntity account;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public DeviceType getType() {
        return type;
    }

    public void setType(final DeviceType type) {
        this.type = type;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(final String deviceId) {
        this.deviceId = deviceId;
    }

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


    public AccountEntity getAccount() {
        return account;
    }

    public void setAccount(final AccountEntity account) {
        this.account = account;
    }

    public DeviceStatus getStatus() {
        return status;
    }

    public void setStatus(final DeviceStatus status) {
        this.status = status;
    }
}
