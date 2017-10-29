package com.algalopez.mycon.wifi.data.storage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.algalopez.mycon.wifi.data.storage.database.contract.WifiContract;
import com.algalopez.mycon.wifi.data.storage.database.helper.WifiDbHelper;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    10/29/17
 */

public class DeviceDatabase implements IDeviceDatabase {


    private static final String LOGTAG = "DeviceDatabase";

    private WifiDbHelper mDbHelper;


    public DeviceDatabase(Context context){

        mDbHelper = new WifiDbHelper(context);
    }

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
        String[] projection = WifiContract.DeviceEntry.getProjection();
        String selection = WifiContract.DeviceEntry._ID + " = ?";
        String[] selectionArgs = new String[] { id.toString() };
        Cursor c = database.query(WifiContract.DeviceEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        if (c.moveToFirst()){

            values.put(WifiContract.DeviceEntry._ID, c.getString(c.getColumnIndex(WifiContract.DeviceEntry._ID)));
            values.put(WifiContract.DeviceEntry.COLUMN_MAC, c.getString(c.getColumnIndex(WifiContract.DeviceEntry.COLUMN_MAC)));
            values.put(WifiContract.DeviceEntry.COLUMN_BRAND, c.getString(c.getColumnIndex(WifiContract.DeviceEntry.COLUMN_BRAND)));
            values.put(WifiContract.DeviceEntry.COLUMN_NAME, c.getString(c.getColumnIndex(WifiContract.DeviceEntry.COLUMN_NAME)));
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
        rowID = database.insert(WifiContract.DeviceEntry.TABLE_NAME, null, values);
        database.close();

        return rowID;
    }


    @Override
    public Integer deleteDevice(Long id){

        SQLiteDatabase database;
        String whereClause = WifiContract.DeviceEntry._ID + " = ?";
        String whereClauseConnection = WifiContract.WifiConnectDeviceEntry.COLUMN_DEVICE + " = ?";
        String[] whereArgs = new String[] {id.toString()};
        int rowsAffected;

        // Delete device from both tables
        database = mDbHelper.getWritableDatabase();
        rowsAffected = database.delete(WifiContract.DeviceEntry.TABLE_NAME, whereClause, whereArgs);
        rowsAffected += database.delete(WifiContract.WifiConnectDeviceEntry.TABLE_NAME, whereClauseConnection, whereArgs);
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

        Long deviceID = values.getAsLong(WifiContract.DeviceEntry._ID);

        int rowsAffected;
        String whereClause = WifiContract.DeviceEntry._ID + " = ?";
        String[] whereArgs = new String[] {deviceID.toString()};
        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        rowsAffected = database.update(WifiContract.DeviceEntry.TABLE_NAME, values, whereClause, whereArgs);
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

        String[] projection = WifiContract.DeviceEntry.getProjection();
        String selection = WifiContract.DeviceEntry.COLUMN_MAC + " = ?";
        String[] selectionArgs = new String[] { MAC };
        Cursor c = database.query(WifiContract.DeviceEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);

        if (c.moveToFirst()){

            id = c.getLong(c.getColumnIndex(WifiContract.DeviceEntry._ID));
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

        String[] projection = WifiContract.WifiEntry.getProjection();
        Cursor c = database.query(WifiContract.DeviceEntry.TABLE_NAME, projection, null, null, null, null, null);


        if (c.moveToFirst()){
            do {
                deviceValues = new ContentValues();
                deviceValues.put(WifiContract.DeviceEntry._ID, c.getLong(c.getColumnIndex(WifiContract.DeviceEntry._ID)));
                deviceValues.put(WifiContract.DeviceEntry.COLUMN_MAC, c.getString(c.getColumnIndex(WifiContract.DeviceEntry.COLUMN_MAC)));
                deviceValues.put(WifiContract.DeviceEntry.COLUMN_NAME, c.getString(c.getColumnIndex(WifiContract.DeviceEntry.COLUMN_NAME)));
                deviceValues.put(WifiContract.DeviceEntry.COLUMN_BRAND, c.getString(c.getColumnIndex(WifiContract.DeviceEntry.COLUMN_BRAND)));
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

        removedRows = database.delete(WifiContract.DeviceEntry.TABLE_NAME, null, null);
        database.close();

        return removedRows;
    }

}
