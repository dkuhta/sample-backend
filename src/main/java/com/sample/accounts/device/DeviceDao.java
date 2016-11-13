package com.sample.accounts.device;

import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * Created by dkuhta on 27.4.15.
 */
@ImplementedBy(DeviceDaoBean.class)
public interface DeviceDao {

    void persist(DeviceEntity entity);

    DeviceEntity merge(DeviceEntity entity);

    void remove(DeviceEntity entity);

    List<DeviceEntity> findAllByAccountId(final Long personId);

    DeviceEntity findByDevice(final String deviceId, final DeviceType type);

    DeviceEntity findByDeviceAndAccountEamil(String deviceId, DeviceType device, String email);
}
