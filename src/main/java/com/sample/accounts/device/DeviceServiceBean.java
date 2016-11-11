package com.sample.accounts.device;

import com.google.inject.Inject;
import com.sample.accounts.AccountEntity;
import com.softteco.toolset.restlet.UserSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.MessageFormat;

/**
 * Created on 11.11.16.
 *
 * @author Denis Kuhta
 * @since JDK1.8
 */
public class DeviceServiceBean implements DeviceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeviceServiceBean.class);

    @Inject
    private DeviceDao deviceDao;

    @Inject
    private DeviceDtoAssembler deviceDtoAssembler;

    @Inject
    private UserSession userSession;

    @Override
    public void login(final DeviceDto deviceDto, final AccountEntity accountE) {
        DeviceEntity deviceE = deviceDao.findByDevice(deviceDto.deviceId, deviceDto.type);
        if (deviceE == null) {
            deviceE = new DeviceEntity();
            deviceDtoAssembler.disassemble(deviceE, deviceDto);
            deviceE.setAccount(accountE);
            deviceE.setStatus(DeviceStatus.LOGGED_IN);
            deviceDao.persist(deviceE);
        } else {
            deviceDtoAssembler.disassemble(deviceE, deviceDto);
            deviceE.setAccount(accountE);
            deviceE.setStatus(DeviceStatus.LOGGED_IN);
            deviceDao.merge(deviceE);
        }
    }

    @Override
    public void logout(final DeviceLogoutDto deviceDto) {
        final DeviceEntity deviceE = deviceDao.findByDeviceAndAccountEamil(deviceDto.deviceId, deviceDto.type, userSession.getUsername());
        if (deviceE == null) {
            LOGGER.error(MessageFormat.format("Device with id {0} for user with email {1} not found", deviceDto.deviceId, userSession.getUsername()));
            return;
        }
        deviceE.setStatus(DeviceStatus.LOGGED_OUT);
        deviceDao.merge(deviceE);
    }
}
