package com.algalopez.mycon.wifi.data.manager;

import com.algalopez.mycon.wifi.model.DeviceEntity;
import com.algalopez.mycon.wifi.model.WifiEntity;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/5/17
 */

public interface IWifiManagerRepo {


    /* *********************************************************************************************
     * WIFI
     * *********************************************************************************************
     */


    boolean isWifiConnected();

    WifiEntity getWifi();


    /* *********************************************************************************************
     * DEVICE
     * *********************************************************************************************
     */


    DeviceEntity getDevice(String addrStr);

    //ArrayList<DeviceEntity> getAllDevices();


}
