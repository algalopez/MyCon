package com.algalopez.mycon.wifi.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.algalopez.mycon.wifi.data.database.WifiContract.WifiEntry;
import com.algalopez.mycon.wifi.data.database.WifiContract.DeviceEntry;
import com.algalopez.mycon.wifi.data.database.WifiContract.WifiConnectDeviceEntry;

import java.util.ArrayList;


/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/23/17
 */


public class WifiRepository {

    private static final String LOGTAG = "WifiRepository";

    private WifiDbHelper mDbHelper;


    public WifiRepository(Context context){

        mDbHelper = new WifiDbHelper(context);
    }


    /* *********************************************************************************************
     * WIFI
     * *********************************************************************************************
     */


    /**
     * Query Wifi network by ID
     *
     * @param id Wifi network id
     * @return ContentValues with wifi network data
     */
    ContentValues queryWifi(Long id){

        SQLiteDatabase database;
        ContentValues values = new ContentValues();

        // Check Params
        if (id == null) {
            return values;
        }

        database = mDbHelper.getReadableDatabase();

        String[] projection = WifiEntry.getProjection();
        String selection = WifiEntry._ID + " = ?";
        String[] selectionArgs = new String[] { id.toString() };
        Cursor c = database.query(WifiEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        if (c.moveToFirst()){

            values.put(WifiEntry._ID, c.getString(c.getColumnIndex(WifiEntry._ID)));
            values.put(WifiEntry.COLUMN_SSID, c.getString(c.getColumnIndex(WifiEntry.COLUMN_SSID)));
            values.put(WifiEntry.COLUMN_PASSWORD, c.getString(c.getColumnIndex(WifiEntry.COLUMN_PASSWORD)));
        }

        c.close();
        database.close();

        return values;
    }


    /**
     * Insert a new Wifi if it doesn't exist
     *
     * @param SSID Wifi Network SSID
     * @param password Wifi network password
     * @return ID of the Wifi network or -1 if error
     */
    public Long insertWifi(String SSID, String password){

        Long rowID;
        SQLiteDatabase database;

        // 1.- Check params
        if (SSID == null) {
            return -1L;
        }

        // 2.- Check if the Wifi Network is already in database
        Long wifiID = getWifiIDBySSID(SSID);
        if (wifiID >= 0){
            Log.d(LOGTAG, "insertWifi: Wifi network already exist");
            return -1L;
        }

        // 3.- Insert a new Wifi Network into the database
        database = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(WifiEntry.COLUMN_SSID, SSID);
        values.put(WifiEntry.COLUMN_PASSWORD, password);
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
    public Integer deleteWifi(Long id){

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
     * @param id Wifi network id
     * @param SSID Wifi network SSID
     * @param password Wifi network password
     * @return number of rows affected
     */
    public int updateWifi(Long id, String SSID, String password){

        int rowsAffected = 0;

        // 1.- Check params
        if (SSID == null || id == null) {
            return rowsAffected;
        }

        // 2.- Check if the Wifi Network is already in database
        Long wifiID = getWifiIDBySSID(SSID);
        if (wifiID < 0) {
            Log.d(LOGTAG, "updateWifi: Wifi network doesn't exist");
            return rowsAffected;
        }

        if (!wifiID.equals(id)){
            Log.e(LOGTAG, "updateWifi: ID is different in database");
            return rowsAffected;
        }

        SQLiteDatabase database;
        String whereClause = WifiEntry._ID + " = ?";
        String[] whereArgs = new String[] {id.toString()};
        database = mDbHelper.getWritableDatabase();

        // Update Wifi in database
        ContentValues values = new ContentValues();
        values.put(WifiEntry.COLUMN_SSID, SSID);
        values.put(WifiEntry.COLUMN_PASSWORD, password);
        rowsAffected = database.update(WifiEntry.TABLE_NAME, values, whereClause, whereArgs);

        if (rowsAffected > 1){
            Log.d(LOGTAG, "updateWifi: More than one row affected");
        }

        database.close();

        return rowsAffected;
    }


    /**
     * Returns the wifi ID form a SSID
     *
     * @param SSID Wifi Network SSID
     * @return Wifi network id
     */
    public Long getWifiIDBySSID(String SSID){

        SQLiteDatabase database;
        Long id = -1L;

        // Check Params
        if (SSID == null) {
            return -1L;
        }

        database = mDbHelper.getReadableDatabase();

        String[] projection = WifiEntry.getProjection();
        String selection = WifiEntry.COLUMN_SSID + " = ?";
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


    /* *********************************************************************************************
     * DEVICE
     * *********************************************************************************************
     */



    public ContentValues queryDevice(Long id){

        SQLiteDatabase database;
        ContentValues values = new ContentValues();

        database = mDbHelper.getReadableDatabase();
        String[] projection = DeviceEntry.getProjection();
        String selection = DeviceEntry._ID + " = ?";
        String[] selectionArgs = new String[] { id.toString() };
        Cursor c = database.query(DeviceEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        if (c.moveToFirst()){

            values.put(DeviceEntry._ID, c.getString(c.getColumnIndex(DeviceEntry._ID)));
            values.put(DeviceEntry.COLUMN_MAC, c.getString(c.getColumnIndex(DeviceEntry.COLUMN_MAC)));
            values.put(DeviceEntry.COLUMN_BRAND, c.getString(c.getColumnIndex(DeviceEntry.COLUMN_BRAND)));
            values.put(DeviceEntry.COLUMN_NAME, c.getString(c.getColumnIndex(DeviceEntry.COLUMN_NAME)));
        }

        c.close();
        database.close();

        return values;
    }


    public Long insertDevice(String MAC, String brand, String name){

        Long rowID;
        SQLiteDatabase database;

        // 1.- Check params
        if (MAC == null) {
            return -1L;
        }

        Long deviceID = getDeviceIDByMAC(MAC);
        if (deviceID >= 0){
            Log.d(LOGTAG, "insertDevice: Device already exist");
            return -1L;
        }

        database = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DeviceEntry.COLUMN_MAC, MAC);
        values.put(DeviceEntry.COLUMN_BRAND, brand);
        values.put(DeviceEntry.COLUMN_NAME, name);
        rowID = database.insert(DeviceEntry.TABLE_NAME, null, values);

        database.close();
        return rowID;
    }


    public Integer deleteDevice(Long id){

        SQLiteDatabase database;
        String whereClause = DeviceEntry._ID + " = ?";
        String whereClauseConnection = WifiConnectDeviceEntry.COLUMN_DEVICE + " = ?";
        String[] whereArgs = new String[] {id.toString()};
        int rowsAffected;

        // Delete device from both tables
        database = mDbHelper.getWritableDatabase();
        rowsAffected = database.delete(DeviceEntry.TABLE_NAME, whereClause, whereArgs);
        rowsAffected += database.delete(WifiConnectDeviceEntry.TABLE_NAME, whereClauseConnection, whereArgs);
        database.close();

        return rowsAffected;
    }


    public Integer updateDevice(Long id, String MAC, String brand, String name){


        int rowsAffected;
        String whereClause = DeviceEntry._ID + " = ?";
        String[] whereArgs = new String[] {id.toString()};
        SQLiteDatabase database = mDbHelper.getWritableDatabase();


        // Update Wifi in database
        ContentValues values = new ContentValues();
        values.put(DeviceEntry.COLUMN_MAC, MAC);
        values.put(DeviceEntry.COLUMN_BRAND, brand);
        values.put(DeviceEntry.COLUMN_NAME, name);
        rowsAffected = database.update(DeviceEntry.TABLE_NAME, values, whereClause, whereArgs);

        if (rowsAffected > 1){
            Log.d(LOGTAG, "updateDevice: More than one row affected");
        }

        database.close();

        return rowsAffected;
    }


    /**
     * Get device ID from MAC address
     *
     * @param MAC Device MAC address
     * @return device ID
     */
    public Long getDeviceIDByMAC(String MAC) {

        SQLiteDatabase database;
        Long id = -1L;

        // Check Params
        if (MAC == null) {
            return -1L;
        }

        database = mDbHelper.getReadableDatabase();

        String[] projection = DeviceEntry.getProjection();
        String selection = DeviceEntry.COLUMN_MAC + " = ?";
        String[] selectionArgs = new String[] { MAC };
        Cursor c = database.query(DeviceEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        if (c.moveToFirst()){

            id = c.getLong(c.getColumnIndex(DeviceEntry._ID));
        }

        c.close();
        database.close();
        return id;
    }


    /* *********************************************************************************************
     * CONNECTED DEVICES
     * *********************************************************************************************
     */


    public void addConnection(Long wifiID, Long deviceID, String ip){
        SQLiteDatabase database;

        database = mDbHelper.getWritableDatabase();
        int removedRows;

        // Remove previous connections
        String whereClause = WifiConnectDeviceEntry.COLUMN_WIFI + " = ? AND " + WifiConnectDeviceEntry.COLUMN_DEVICE + " = ?";
        String[] whereArgs = new String[]{wifiID.toString(), deviceID.toString()};
        removedRows = database.delete(WifiConnectDeviceEntry.TABLE_NAME, whereClause, whereArgs);
        Log.d(LOGTAG, "addConnection: removed " + removedRows + " rows");

        ContentValues values = new ContentValues();
        values.put(WifiConnectDeviceEntry.COLUMN_WIFI, wifiID);
        values.put(WifiConnectDeviceEntry.COLUMN_DEVICE, deviceID);
        values.put(WifiConnectDeviceEntry.COLUMN_IP, ip);
        database.insert(WifiConnectDeviceEntry.TABLE_NAME, null, values);

        database.close();
    }


    public int removeAll(){

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int removedRows = 0;

        removedRows += database.delete(WifiConnectDeviceEntry.TABLE_NAME, null, null);
        removedRows += database.delete(WifiEntry.TABLE_NAME, null, null);
        removedRows += database.delete(DeviceEntry.TABLE_NAME, null, null);
        database.close();

        return removedRows;

    }

    public ArrayList<ContentValues> queryWifiConnections(Long wifiID){

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        String[] projection = WifiConnectDeviceEntry.getProjection();
        String selection = WifiConnectDeviceEntry.COLUMN_WIFI + " = ?";
        String[] selectionArgs = new String[] { wifiID.toString() };
        String orderBy = WifiConnectDeviceEntry.COLUMN_IP;
        Cursor c = database.query(WifiConnectDeviceEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);

        ArrayList<ContentValues> ret = new ArrayList<>();
        ContentValues values;

        if (c.moveToFirst()) {

            do {
                values = new ContentValues();
                values.put(WifiConnectDeviceEntry.COLUMN_WIFI, c.getLong(c.getColumnIndex(WifiConnectDeviceEntry.COLUMN_WIFI)));
                values.put(WifiConnectDeviceEntry.COLUMN_DEVICE, c.getLong(c.getColumnIndex(WifiConnectDeviceEntry.COLUMN_DEVICE)));
                values.put(WifiConnectDeviceEntry.COLUMN_IP, c.getString(c.getColumnIndex(WifiConnectDeviceEntry.COLUMN_IP)));
                ret.add(values);
            } while (c.moveToNext());
        }

        c.close();
        database.close();

        return ret;
    }
}
