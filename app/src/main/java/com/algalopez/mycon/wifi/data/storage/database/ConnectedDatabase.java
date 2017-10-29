package com.algalopez.mycon.wifi.data.storage.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.algalopez.mycon.wifi.data.storage.database.contract.WifiContract;
import com.algalopez.mycon.wifi.data.storage.database.helper.WifiDbHelper;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    10/29/17
 */

public class ConnectedDatabase implements IConnectedDatabase {


    private static final String LOGTAG = "ConnectedDatabase";

    private WifiDbHelper mDbHelper;


    public ConnectedDatabase(Context context){

        mDbHelper = new WifiDbHelper(context);
    }


    /**
     *
     * @param values -
     */
    @Override
    public void insertConnection(ContentValues values){

        SQLiteDatabase database;
        Long wifiID = values.getAsLong(WifiContract.WifiConnectDeviceEntry.COLUMN_WIFI);
        Long deviceID = values.getAsLong(WifiContract.WifiConnectDeviceEntry.COLUMN_DEVICE);

        database = mDbHelper.getWritableDatabase();
        int removedRows;

        // Remove previous connections
        String whereClause = WifiContract.WifiConnectDeviceEntry.COLUMN_WIFI + " = ? AND " + WifiContract.WifiConnectDeviceEntry.COLUMN_DEVICE + " = ?";
        String[] whereArgs = new String[]{wifiID.toString(), deviceID.toString()};
        removedRows = database.delete(WifiContract.WifiConnectDeviceEntry.TABLE_NAME, whereClause, whereArgs);

        if(removedRows > 0){

            Log.d(LOGTAG, "addConnection: Removed " + removedRows + " rows");
        }

        database.insert(WifiContract.WifiConnectDeviceEntry.TABLE_NAME, null, values);

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

        String[] projection = WifiContract.WifiConnectDeviceEntry.getProjection();
        String selection = WifiContract.WifiConnectDeviceEntry.COLUMN_WIFI + " = ?";
        String[] selectionArgs = new String[] { wifiID.toString() };
        String orderBy = WifiContract.WifiConnectDeviceEntry.COLUMN_IP;
        Cursor c = database.query(WifiContract.WifiConnectDeviceEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, orderBy);

        ArrayList<ContentValues> ret = new ArrayList<>();
        ContentValues values;

        if (c.moveToFirst()) {

            do {
                values = new ContentValues();
                values.put(WifiContract.WifiConnectDeviceEntry.COLUMN_WIFI, c.getLong(c.getColumnIndex(WifiContract.WifiConnectDeviceEntry.COLUMN_WIFI)));
                values.put(WifiContract.WifiConnectDeviceEntry.COLUMN_DEVICE, c.getLong(c.getColumnIndex(WifiContract.WifiConnectDeviceEntry.COLUMN_DEVICE)));
                values.put(WifiContract.WifiConnectDeviceEntry.COLUMN_IP, c.getString(c.getColumnIndex(WifiContract.WifiConnectDeviceEntry.COLUMN_IP)));
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

        String whereClause = WifiContract.WifiConnectDeviceEntry.COLUMN_WIFI + " = ?";
        String[] whereArgs = new String[] { wifiID.toString() };

        SQLiteDatabase database = mDbHelper.getWritableDatabase();

        int removedRows = database.delete(WifiContract.WifiConnectDeviceEntry.TABLE_NAME, whereClause, whereArgs);
        database.close();

        return removedRows;
    }

}
