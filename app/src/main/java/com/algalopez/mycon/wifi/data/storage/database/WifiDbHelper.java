package com.algalopez.mycon.wifi.data.storage.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.algalopez.mycon.wifi.data.storage.database.WifiContract.WifiEntry;
import com.algalopez.mycon.wifi.data.storage.database.WifiContract.DeviceEntry;
import com.algalopez.mycon.wifi.data.storage.database.WifiContract.WifiConnectDeviceEntry;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/23/17
 */

class WifiDbHelper extends SQLiteOpenHelper {


    private final static int DATABASE_VERSION = 3;
    private final static String DATABASE_NAME = "wifi.db";


    WifiDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        final String SQL_CREATE_WIFI_TABLE = "CREATE TABLE " + WifiEntry.TABLE_NAME + " (" +
                WifiEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WifiEntry.COLUMN_SSID + " TEXT, " +
                WifiEntry.COLUMN_PASSWORD + " TEXT, " +
                WifiEntry.COLUMN_LASTUPDATED + " INTEGER, " +
                WifiEntry.COLUMN_NETMASK + " TEXT, " +
                WifiEntry.COLUMN_BSSID + " TEXT, " +
                WifiEntry.COLUMN_RSSI + " INTEGER, " +
                WifiEntry.COLUMN_FREQUENCY + " INTEGER, " +
                WifiEntry.COLUMN_LINKSPEED + " INTEGER, " +
                WifiEntry.COLUMN_NETWORKID + " INTEGER, " +
                WifiEntry.COLUMN_SERVERADDRESS + " TEXT, " +
                WifiEntry.COLUMN_DNS1 + " TEXT, " +
                WifiEntry.COLUMN_DNS2 + " TEXT " +
                ");";

        final String SQL_CREATE_DEVICE_TABLE = "CREATE TABLE " + DeviceEntry.TABLE_NAME + " (" +
                DeviceEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DeviceEntry.COLUMN_MAC + " TEXT, " +
                DeviceEntry.COLUMN_NAME + " TEXT, " +
                DeviceEntry.COLUMN_BRAND + " TEXT " +
                ");";

        final String SQL_CREATE_WIFI_CONNECT_DEVICE = "CREATE TABLE " + WifiConnectDeviceEntry.TABLE_NAME + " (" +
                WifiConnectDeviceEntry.COLUMN_WIFI + " INTEGER, " +
                WifiConnectDeviceEntry.COLUMN_DEVICE + " INTEGER, " +
                WifiConnectDeviceEntry.COLUMN_IP + " TEXT, " +
                "PRIMARY KEY (" +
                    WifiConnectDeviceEntry.COLUMN_WIFI + " ," +
                    WifiConnectDeviceEntry.COLUMN_DEVICE +
                    "), " +
                "FOREIGN KEY (" +
                    WifiConnectDeviceEntry.COLUMN_WIFI + ") REFERENCES " + WifiEntry.TABLE_NAME + "(" + WifiEntry._ID + "), " +
                "FOREIGN KEY (" +
                    WifiConnectDeviceEntry.COLUMN_DEVICE + ") REFERENCES " + DeviceEntry.TABLE_NAME + "(" + DeviceEntry._ID + ")" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_WIFI_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_DEVICE_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_WIFI_CONNECT_DEVICE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WifiEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + DeviceEntry.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + WifiConnectDeviceEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
