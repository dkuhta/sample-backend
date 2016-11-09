package com.sample.accounts.device;

import com.softteco.toolset.jpa.AbstractJpaDao;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by dkuhta on 27.4.15.
 */
public class DeviceDaoBean extends AbstractJpaDao<DeviceEntity, Long> implements DeviceDao {

    @Override
    public List<DeviceEntity> findAllByPersonId(final Long personId) {
        final Query query = getEntityManager().createNamedQuery(DeviceEntity.FIND_ALL_BY_PERSON_ID);
        query.setParameter("personId", personId);
        return getResultList(query);
    }

    @Override
    public DeviceEntity findByDevice(final String deviceId, final int deviceType) {
        final Query query = getEntityManager().createNamedQuery(DeviceEntity.FIND_BY_DEVICE);
        query.setParameter("deviceId", deviceId);
        query.setParameter("type", deviceType);
        return getSingleResult(query);
    }

    @Override
    public DeviceEntity findByDeviceAndPersonEmail(final String deviceId, final int deviceType, final String email) {
        final Query query = getEntityManager().createNamedQuery(DeviceEntity.FIND_BY_DEVICE_AND_PERSON_EMAIL);
        query.setParameter("deviceId", deviceId);
        query.setParameter("type", deviceType);
        query.setParameter("email", email);
        return getSingleResult(query);
    }
}
