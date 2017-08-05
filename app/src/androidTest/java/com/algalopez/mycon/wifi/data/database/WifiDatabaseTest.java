package com.algalopez.mycon.wifi.data.database;

import android.content.ContentValues;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/29/17
 */
public class WifiDatabaseTest {


    private WifiDatabase mWifiDatabase;

    private final static String T_SSID = "SSID_";
    private final static String T_PASSWORD = "PASSWORD_";
    private final static Date T_LASTUPDATE = new Date();


    private final static String T_MAC = "MAC_";
    private final static String T_BRAND = "BRAND_";
    private final static String T_NAME = "NAME_";

    private final static String T_IP = "IP_";



    @Before
    public void setUp() throws Exception {
        mWifiDatabase = new WifiDatabase(InstrumentationRegistry.getTargetContext());
        mWifiDatabase.deleteAll();
    }


    @After
    public void tearDown() throws Exception {

    }


    @Test
    public void testInsertQueryWifi() throws Exception {

        ContentValues request_values = new ContentValues();
        ContentValues response_values;

        request_values.put(WifiContract.WifiEntry.COLUMN_SSID, T_SSID);
        request_values.put(WifiContract.WifiEntry.COLUMN_PASSWORD, T_PASSWORD);
        request_values.put(WifiContract.WifiEntry.COLUMN_LASTUPDATED, T_LASTUPDATE.getTime());

        Long wifiID = mWifiDatabase.insertWifi(request_values);

        response_values = mWifiDatabase.queryWifi(wifiID);
        String SSID = response_values.getAsString(WifiContract.WifiEntry.COLUMN_SSID);
        String password = response_values.getAsString(WifiContract.WifiEntry.COLUMN_PASSWORD);
        Date lastUpdate = new Date(response_values.getAsLong(WifiContract.WifiEntry.COLUMN_LASTUPDATED));

        assertEquals("Error with SSID: ", SSID, T_SSID);
        assertEquals("Error with password: ", password, T_PASSWORD);
        assertEquals("Error with lastUpdate: ", lastUpdate, T_LASTUPDATE);
    }


    @Test
    public void testInsertQueryDevice() throws Exception {

        ContentValues request_values = new ContentValues();
        ContentValues response_values;

        request_values.put(WifiContract.DeviceEntry.COLUMN_MAC, T_MAC);
        request_values.put(WifiContract.DeviceEntry.COLUMN_NAME, T_NAME);
        request_values.put(WifiContract.DeviceEntry.COLUMN_BRAND, T_BRAND);

        Long deviceID = mWifiDatabase.insertDevice(request_values);

        response_values = mWifiDatabase.queryDevice(deviceID);
        String MAC = response_values.getAsString(WifiContract.DeviceEntry.COLUMN_MAC);
        String brand = response_values.getAsString(WifiContract.DeviceEntry.COLUMN_BRAND);
        String name = response_values.getAsString(WifiContract.DeviceEntry.COLUMN_NAME);

        assertEquals("Error with MAC: ", T_MAC, MAC);
        assertEquals("Error with name: ", T_NAME, name);
        assertEquals("Error with brand: ", T_BRAND, brand);
    }


    @Test
    public void TestInsertQueryConnected() throws Exception {

        testInsertQueryWifi();
        testInsertQueryDevice();

        ContentValues request_values = new ContentValues();
        ContentValues response_values;

        Long wifiID = mWifiDatabase.getWifiIDBySSID(T_SSID);
        Long deviceID = mWifiDatabase.getDeviceIDByMAC(T_MAC);

        request_values.put(WifiContract.WifiConnectDeviceEntry.COLUMN_WIFI, wifiID);
        request_values.put(WifiContract.WifiConnectDeviceEntry.COLUMN_DEVICE, deviceID);
        request_values.put(WifiContract.WifiConnectDeviceEntry.COLUMN_IP, T_IP);

        mWifiDatabase.insertConnection(request_values);

        ArrayList<ContentValues> connectedList = mWifiDatabase.queryWifiConnections(wifiID);
        response_values = connectedList.get(0);

        String ip = response_values.getAsString(WifiContract.WifiConnectDeviceEntry.COLUMN_IP);
        assertEquals("Error with IP: ", T_IP, ip);

    }







}