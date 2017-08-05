package com.algalopez.mycon.wifi.data;



import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/29/17
 */

public interface IWifiDbRepo {


    /* *********************************************************************************************
     * WIFI
     * *********************************************************************************************
     */


    WifiEntity getWifi(Long wifiID);

    Long storeWifi(WifiEntity newWifiEntity);

    int removeWifi(Long wifiID);

    int updateWifi(WifiEntity newWifiEntity);

    ArrayList<WifiEntity> getAllWifi();

    int removeAll();


    /* *********************************************************************************************
     * DEVICE
     * *********************************************************************************************
     */


    DeviceEntity getDevice(Long deviceID);

    Long storeDevice(DeviceEntity newDeviceEntity);

    int removeDevice(Long deviceID);

    int updateDevice(Long deviceID, DeviceEntity newDeviceEntity);

    ArrayList<DeviceEntity> getAllDevices();

    int removeAllDevices();


    /* *********************************************************************************************
     * CONNECTED DEVICES
     * *********************************************************************************************
     */


    ArrayList<DeviceEntity> getConnectedDevices(Long wifiID);

    int removeConnectedDevices(Long wifiID);



}
