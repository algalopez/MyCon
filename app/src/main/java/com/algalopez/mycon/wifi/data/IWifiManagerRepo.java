package com.algalopez.mycon.wifi.data;

import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.net.InetAddress;
import java.util.ArrayList;

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
