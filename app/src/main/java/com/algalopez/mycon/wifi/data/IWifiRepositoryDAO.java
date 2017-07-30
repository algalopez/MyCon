package com.algalopez.mycon.wifi.data;



import android.support.annotation.Nullable;

import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/29/17
 */

public interface IWifiRepositoryDAO {


    ArrayList<DeviceEntity> getConnectedDevices(Long wifiID);


    WifiEntity getWifi(Long wifiID);
    Long storeWifi(WifiEntity newWifiEntity);
    int removeWifi(Long wifiID);
    int updateWifi(Long wifiID, WifiEntity newWifiEntity);


    DeviceEntity getDevice(Long deviceID);
    Long storeDevice(DeviceEntity newDeviceEntity);
    int removeDevice(Long deviceID);
    int updateDevice(Long deviceID, DeviceEntity newDeviceEntity);


    ArrayList<WifiEntity> getAllWifi();
    ArrayList<DeviceEntity> getAllDevices();
    int removeAllWifi();
    int removeAllDevices();




}
