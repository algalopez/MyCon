package com.algalopez.mycon.wifi.data.storage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.algalopez.mycon.wifi.data.storage.database.contract.WifiContract.WifiEntry;
import com.algalopez.mycon.wifi.data.storage.database.contract.WifiContract.DeviceEntry;
import com.algalopez.mycon.wifi.data.storage.database.contract.WifiContract.WifiConnectDeviceEntry;
import com.algalopez.mycon.wifi.data.storage.database.helper.WifiDbHelper;

import java.util.ArrayList;


/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/23/17
 */


public class WifiDatabase implements IWifiDatabase {

    private static final String LOGTAG = "WifiDatabase";

    private WifiDbHelper mDbHelper;


    public WifiDatabase(Context context){

        mDbHelper = new WifiDbHelper(context);
    }


    /* *********************************************************************************************
     * WIFI
     * *********************************************************************************************
     */


    /**
     * Query Wifi network by ID.
     *
     * @param id Wifi network id
     * @return ContentValues with wifi network data
     */
    @Override
    public ContentValues queryWifi(Long id){

        SQLiteDatabase database;
        ContentValues values = new ContentValues();

        database = mDbHelper.getReadableDatabase();

        String[] projection = WifiEntry.getProjection();
        String selection = WifiEntry._ID + " = ?";
        String[] selectionArgs = new String[] { id.toString() };
        Cursor c = database.query(WifiEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        if (c.moveToFirst()){

            values.put(WifiEntry._ID, c.getString(c.getColumnIndex(WifiEntry._ID)));
            values.put(WifiEntry.COLUMN_SSID, c.getString(c.getColumnIndex(WifiEntry.COLUMN_SSID)));
            values.put(WifiEntry.COLUMN_PASSWORD, c.getString(c.getColumnIndex(WifiEntry.COLUMN_PASSWORD)));
            values.put(WifiEntry.COLUMN_LASTUPDATED, c.getLong(c.getColumnIndex(WifiEntry.COLUMN_LASTUPDATED)));
            values.put(WifiEntry.COLUMN_NETMASK, c.getString(c.getColumnIndex(WifiEntry.COLUMN_NETMASK)));
            values.put(WifiEntry.COLUMN_BSSID, c.getString(c.getColumnIndex(WifiEntry.COLUMN_BSSID)));
            values.put(WifiEntry.COLUMN_RSSI, c.getLong(c.getColumnIndex(WifiEntry.COLUMN_RSSI)));
            values.put(WifiEntry.COLUMN_FREQUENCY, c.getLong(c.getColumnIndex(WifiEntry.COLUMN_FREQUENCY)));
            values.put(WifiEntry.COLUMN_LINKSPEED, c.getLong(c.getColumnIndex(WifiEntry.COLUMN_LINKSPEED)));
            values.put(WifiEntry.COLUMN_NETWORKID, c.getLong(c.getColumnIndex(WifiEntry.COLUMN_NETWORKID)));
            values.put(WifiEntry.COLUMN_SERVERADDRESS, c.getString(c.getColumnIndex(WifiEntry.COLUMN_SERVERADDRESS)));
            values.put(WifiEntry.COLUMN_DNS1, c.getString(c.getColumnIndex(WifiEntry.COLUMN_DNS1)));
            values.put(WifiEntry.COLUMN_DNS2, c.getString(c.getColumnIndex(WifiEntry.COLUMN_DNS2)));
        }

        c.close();
        database.close();

        return values;
    }


    /**
     * Insert a new wifi into the database
     *
     * @param values ContentValues
     * @return new wifi id or -1 if error
     */
    @Override
    public Long insertWifi(ContentValues values) {

        Long rowID;
        SQLiteDatabase database;

        database = mDbHelper.getWritableDatabase();
        rowID = database.insert(WifiEntry.TABLE_NAME, null, values);
        database.close();

        return rowID;
    }


    /**
     * Delete wifi network
     *
     * @param id Wifi network id
     * @return Number of rows affected
     */
    @Override
    public int deleteWifi(Long id){

        SQLiteDatabase database;
        String whereClause = WifiEntry._ID + " = ?";
        String whereClauseConnection = WifiConnectDeviceEntry.COLUMN_WIFI + " = ?";
        String[] whereArgs = new String[] {id.toString()};
        int rowsAffected;

        // Delete from both tables
        database = mDbHelper.getWritableDatabase();
        rowsAffected = database.delete(WifiEntry.TABLE_NAME, whereClause, whereArgs);
        rowsAffected += database.delete(WifiConnectDeviceEntry.TABLE_NAME, whereClauseConnection, whereArgs);
        database.close();

        return rowsAffected;
    }



    /**
     * Update Wifi network data if it exists
     *
     * @param values ContentValues
     * @return number of rows affected by update
     */
    @Override
    public int updateWifi(ContentValues values) {

        int rowsAffected;

        Long wifiID = values.getAsLong(WifiEntry._ID);

        SQLiteDatabase database;
        String whereClause = WifiEntry._ID + " = ?";
        String[] whereArgs = new String[] {wifiID.toString()};
        database = mDbHelper.getWritableDatabase();

        rowsAffected = database.update(WifiEntry.TABLE_NAME, values, whereClause, whereArgs);
        database.close();

        if (rowsAffected > 1){
            Log.d(LOGTAG, "updateWifi: More than one row affected");
        }

        return rowsAffected;
    }


    /**
     * Returns the wifi ID form a SSID
     *
     * @param SSID Wifi Network SSID
     * @return Wifi network id
     */
    @Override
    public Long getWifiIDBySSID(String SSID){

        SQLiteDatabase database;
        Long id = -1L;

        database = mDbHelper.getReadableDatabase();

        String[] projection = WifiEntry.getProjection();
        String selection = WifiEntry.COLUMN_SSID + " = ? ";
        String[] selectionArgs = new String[] { SSID };
        Cursor c = database.query(WifiEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        Log.d(LOGTAG, "getWifiIDBySSID: cursor size is " + c.getCount());

        if (c.moveToFirst()){

            id = c.getLong(c.getColumnIndex(WifiEntry._ID));
        }

        c.close();
        database.close();
        return id;
    }


    /**
     *
     * @return -
     */
    @Override
    public ArrayList<ContentValues> queryAllWifi() {

        ArrayList<ContentValues> wifiList = new ArrayList<>();
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        ContentValues wifiValues;

        String[] projection = WifiEntry.getProjection();
        Cursor c = database.query(WifiEntry.TABLE_NAME, projection, null, null, null, null, null);


        if (c.moveToFirst()){
            do {
                wifiValues = new ContentValues();
                wifiValues.put(WifiEntry._ID, c.getLong(c.getColumnIndex(WifiEntry._ID)));
                wifiValues.put(WifiEntry.COLUMN_SSID, c.getString(c.getColumnIndex(WifiEntry.COLUMN_SSID)));
                wifiValues.put(WifiEntry.COLUMN_PASSWORD, c.getString(c.getColumnIndex(WifiEntry.COLUMN_PASSWORD)));
                wifiValues.put(WifiEntry.COLUMN_LASTUPDATED, c.getLong(c.getColumnIndex(WifiEntry.COLUMN_LASTUPDATED)));
                wifiValues.put(WifiEntry.COLUMN_NETMASK, c.getString(c.getColumnIndex(WifiEntry.COLUMN_NETMASK)));
                wifiValues.put(WifiEntry.COLUMN_BSSID, c.getString(c.getColumnIndex(WifiEntry.COLUMN_BSSID)));
                wifiValues.put(WifiEntry.COLUMN_RSSI, c.getLong(c.getColumnIndex(WifiEntry.COLUMN_RSSI)));
                wifiValues.put(WifiEntry.COLUMN_FREQUENCY, c.getLong(c.getColumnIndex(WifiEntry.COLUMN_FREQUENCY)));
                wifiValues.put(WifiEntry.COLUMN_LINKSPEED, c.getLong(c.getColumnIndex(WifiEntry.COLUMN_LINKSPEED)));
                wifiValues.put(WifiEntry.COLUMN_NETWORKID, c.getLong(c.getColumnIndex(WifiEntry.COLUMN_NETWORKID)));
                wifiValues.put(WifiEntry.COLUMN_SERVERADDRESS, c.getString(c.getColumnIndex(WifiEntry.COLUMN_SERVERADDRESS)));
                wifiValues.put(WifiEntry.COLUMN_DNS1, c.getString(c.getColumnIndex(WifiEntry.COLUMN_DNS1)));
                wifiValues.put(WifiEntry.COLUMN_DNS2, c.getString(c.getColumnIndex(WifiEntry.COLUMN_DNS2)));
                wifiList.add(wifiValues);
            } while (c.moveToNext());
        }
        c.close();
        database.close();

        return wifiList;
    }


    @Override
    public int deleteAllWifi() {
        return 0;
    }
}
