package com.algalopez.mycon.wifi.data.storage.database;

import android.content.ContentValues;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    10/29/17
 */

public interface IDeviceDatabase {




    ContentValues queryDevice(Long id);

    Long insertDevice(ContentValues values);

    Integer deleteDevice(Long id);

    int updateDevice(ContentValues values);

    Long getDeviceIDByMAC(String MAC);

    ArrayList<ContentValues> queryAllDevice();

    int deleteAllDevice();

}
