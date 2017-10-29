package com.algalopez.mycon.wifi.data.storage;



import com.algalopez.mycon.wifi.model.ConnectionEntity;
import com.algalopez.mycon.wifi.model.DeviceEntity;
import com.algalopez.mycon.wifi.model.WifiEntity;

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

    int removeAllWifi();

    Long getWifiIDBySSID(String SSID);


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

    Long getDeviceIDByMAC(String Mac);


    /* *********************************************************************************************
     * CONNECTED DEVICES
     * *********************************************************************************************
     */


    ArrayList<ConnectionEntity> getConnectedDevices(Long wifiID);

    int removeConnectedDevices(Long wifiID);

    int storeConnectedDevices(Long wifiID, ArrayList<ConnectionEntity> connectionEntities);

}
