package com.algalopez.mycon.wifi.data.storage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.algalopez.mycon.wifi.data.storage.database.WifiContract.WifiEntry;
import com.algalopez.mycon.wifi.data.storage.database.WifiContract.DeviceEntry;
import com.algalopez.mycon.wifi.data.storage.database.WifiContract.WifiConnectDeviceEntry;
import com.algalopez.mycon.wifi.data.storage.IWifiStorage;

import java.util.ArrayList;


/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/23/17
 */


public class WifiDatabase implements IWifiStorage {

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
                wifiList.add(wifiValues);
            } while (c.moveToNext());
        }
        c.close();
        database.close();

        return wifiList;
    }


    /* *********************************************************************************************
     * DEVICE
     * *********************************************************************************************
     */


    /**
     *
     * @param id -
     * @return -
     */
    @Override
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


    /**
     *
     *
     * @param values -
     * @return -
     */
    @Override
    public Long insertDevice(ContentValues values){

        Long rowID;
        SQLiteDatabase database;

        database = mDbHelper.getWritableDatabase();
        rowID = database.insert(DeviceEntry.TABLE_NAME, null, values);
        database.close();

        return rowID;
    }


    @Override
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


    /**
     *
     * @param values -
     * @return -
     */
    @Override
    public int updateDevice(ContentValues values){

        Long deviceID = values.getAsLong(DeviceEntry._ID);

        int rowsAffected;
        String whereClause = DeviceEntry._ID + " = ?";
        String[] whereArgs = new String[] {deviceID.toString()};
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        rowsAffected = database.update(DeviceEntry.TABLE_NAME, values, whereClause, whereArgs);
        database.close();

        return rowsAffected;
    }


    /**
     * Get device ID from MAC address
     *
     * @param MAC Device MAC address
     * @return device ID
     */
    @Override
    public Long getDeviceIDByMAC(String MAC) {

        SQLiteDatabase database;
        Long id = -1L;

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


    /**
     *
     * @return -
     */
    @Override
    public ArrayList<ContentValues> queryAllDevice() {

        ArrayList<ContentValues> deviceList = new ArrayList<>();
        SQLiteDatabase database = mDbHelper.getReadableDatabase();
        ContentValues deviceValues;

        String[] projection = WifiEntry.getProjection();
        Cursor c = database.query(DeviceEntry.TABLE_NAME, projection, null, null, null, null, null);


        if (c.moveToFirst()){
            do {
                deviceValues = new ContentValues();
                deviceValues.put(DeviceEntry._ID, c.getLong(c.getColumnIndex(DeviceEntry._ID)));
                deviceValues.put(DeviceEntry.COLUMN_MAC, c.getString(c.getColumnIndex(DeviceEntry.COLUMN_MAC)));
                deviceValues.put(DeviceEntry.COLUMN_NAME, c.getString(c.getColumnIndex(DeviceEntry.COLUMN_NAME)));
                deviceValues.put(DeviceEntry.COLUMN_BRAND, c.getString(c.getColumnIndex(DeviceEntry.COLUMN_BRAND)));
                deviceList.add(deviceValues);
            } while (c.moveToNext());
        }
        c.close();
        database.close();

        return deviceList;
    }


    /**
     *
     * @return -
     */
    @Override
    public int deleteAllDevice() {

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int removedRows;

        removedRows = database.delete(DeviceEntry.TABLE_NAME, null, null);
        database.close();

        return removedRows;
    }


    /* *********************************************************************************************
     * CONNECTED DEVICES
     * *********************************************************************************************
     */


    /**
     *
     * @param values -
     */
    @Override
    public void insertConnection(ContentValues values){

        SQLiteDatabase database;
        Long wifiID = values.getAsLong(WifiConnectDeviceEntry.COLUMN_WIFI);
        Long deviceID = values.getAsLong(WifiConnectDeviceEntry.COLUMN_DEVICE);

        database = mDbHelper.getWritableDatabase();
        int removedRows;

        // Remove previous connections
        String whereClause = WifiConnectDeviceEntry.COLUMN_WIFI + " = ? AND " + WifiConnectDeviceEntry.COLUMN_DEVICE + " = ?";
        String[] whereArgs = new String[]{wifiID.toString(), deviceID.toString()};
        removedRows = database.delete(WifiConnectDeviceEntry.TABLE_NAME, whereClause, whereArgs);

        if(removedRows > 0){

            Log.d(LOGTAG, "addConnection: Removed " + removedRows + " rows");
        }

        database.insert(WifiConnectDeviceEntry.TABLE_NAME, null, values);

        database.close();
    }


    /**
     *
     * @param wifiID -
     * @return -
     */
    @Override
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


    /**
     *
     * @param wifiID -
     * @return -
     */
    @Override
    public int deleteAllConnectedDevices(Long wifiID) {

        String whereClause = WifiConnectDeviceEntry.COLUMN_WIFI + " = ?";
        String[] whereArgs = new String[] { wifiID.toString() };

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int removedRows = database.delete(WifiConnectDeviceEntry.TABLE_NAME, whereClause, whereArgs);
        database.close();

        return removedRows;
    }


    /* *********************************************************************************************
     * ALL
     * *********************************************************************************************
     */


    /**
     *
     * @return -
     */
    @Override
    public int deleteAll(){

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int removedRows = 0;

        removedRows += database.delete(WifiConnectDeviceEntry.TABLE_NAME, null, null);
        removedRows += database.delete(WifiEntry.TABLE_NAME, null, null);
        removedRows += database.delete(DeviceEntry.TABLE_NAME, null, null);
        database.close();

        return removedRows;
    }
}
