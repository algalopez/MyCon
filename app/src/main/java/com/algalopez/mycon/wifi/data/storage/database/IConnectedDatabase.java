package com.algalopez.mycon.wifi.data.storage.database;

import android.content.ContentValues;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    10/29/17
 */

public interface IConnectedDatabase {

    void insertConnection(ContentValues values);

    ArrayList<ContentValues> queryWifiConnections(Long wifiID);

    int deleteAllConnectedDevices(Long wifiID);
}
