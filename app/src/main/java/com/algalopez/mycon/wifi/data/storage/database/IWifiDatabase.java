package com.algalopez.mycon.wifi.data.storage.database;

import android.content.ContentValues;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/5/17
 */

public interface IWifiDatabase {


    ContentValues queryWifi(Long id);

    Long insertWifi(ContentValues values);

    int deleteWifi(Long id);

    int updateWifi(ContentValues values);

    Long getWifiIDBySSID(String SSID);

    ArrayList<ContentValues> queryAllWifi();

    int deleteAllWifi();



}
