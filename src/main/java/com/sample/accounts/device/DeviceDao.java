package com.sample.accounts.device;

import com.google.inject.ImplementedBy;

import java.util.List;

/**
 * Created by dkuhta on 27.4.15.
 */
@ImplementedBy(DeviceDaoBean.class)
public interface DeviceDao {

    void persist(final DeviceEntity entity);

    DeviceEntity merge(final DeviceEntity entity);

    void remove(final DeviceEntity entity);

    List<DeviceEntity> findAllByPersonId(final Long personId);

    DeviceEntity findByDevice(final String deviceId, final int deviceType);

    DeviceEntity findByDeviceAndPersonEmail(final String deviceId, final int deviceType, final String email);
}
