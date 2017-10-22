package com.algalopez.mycon.wifi.domain.repository;

import com.algalopez.mycon.wifi.domain.model.DeviceEntity;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    10/22/17
 */

public interface IDeviceDbRepo {

    DeviceEntity getDevice(Long deviceID);

    Long storeDevice(DeviceEntity newDeviceEntity);

    int removeDevice(Long deviceID);

    int updateDevice(Long deviceID, DeviceEntity newDeviceEntity);

    ArrayList<DeviceEntity> getAllDevices();

    int removeAllDevices();

    Long getDeviceIDByMAC(String Mac);
}
