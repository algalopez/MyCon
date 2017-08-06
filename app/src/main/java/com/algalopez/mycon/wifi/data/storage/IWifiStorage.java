package com.algalopez.mycon.wifi.data.storage;

import android.content.ContentValues;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/5/17
 */

public interface IWifiStorage {


    /* *********************************************************************************************
     * WIFI
     * *********************************************************************************************
     */


    ContentValues queryWifi(Long id);

    Long insertWifi(ContentValues values);

    int deleteWifi(Long id);

    int updateWifi(ContentValues values);

    Long getWifiIDBySSID(String SSID);

    ArrayList<ContentValues> queryAllWifi();


    /* *********************************************************************************************
     * DEVICES
     * *********************************************************************************************
     */


    ContentValues queryDevice(Long id);

    Long insertDevice(ContentValues values);

    Integer deleteDevice(Long id);

    int updateDevice(ContentValues values);

    Long getDeviceIDByMAC(String MAC);

    ArrayList<ContentValues> queryAllDevice();

    int deleteAllDevice();

    /* *********************************************************************************************
     * CONNECTED DEVICES
     * *********************************************************************************************
     */


    void insertConnection(ContentValues values);

    ArrayList<ContentValues> queryWifiConnections(Long wifiID);

    int deleteAllConnectedDevices(Long wifiID);


    /* *********************************************************************************************
     * ALL
     * *********************************************************************************************
     */


    int deleteAll();



}
