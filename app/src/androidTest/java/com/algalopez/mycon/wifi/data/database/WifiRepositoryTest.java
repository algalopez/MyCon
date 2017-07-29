package com.algalopez.mycon.wifi.data.database;

import android.content.ContentValues;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/29/17
 */
public class WifiRepositoryTest {


    private WifiRepository mWifiRepository;
    private String tSSID = "SSID_1";
    private String tPassword = "pass_1";
    private String tMAC = "MAC_1";
    private String tBrand = "brand_1";
    private String tName = "name_1";
    private String tIP = "ip_1";



    @Before
    public void setUp() throws Exception {
        mWifiRepository = new WifiRepository(InstrumentationRegistry.getTargetContext());
        mWifiRepository.removeAll();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testInsertQueryWifi() throws Exception {

        Long wifiID = mWifiRepository.insertWifi(tSSID, tPassword);

        ContentValues values = mWifiRepository.queryWifi(wifiID);
        String SSID = values.getAsString(WifiContract.WifiEntry.COLUMN_SSID);
        String password = values.getAsString(WifiContract.WifiEntry.COLUMN_PASSWORD);

        assertEquals("Error with SSID: ", SSID, tSSID);
        assertEquals("Error with password: ", password, tPassword);
    }


    @Test
    public void testInsertQueryDevice() throws Exception {

        Long deviceID = mWifiRepository.insertDevice(tMAC, tBrand, tName);

        ContentValues values = mWifiRepository.queryDevice(deviceID);
        String MAC = values.getAsString(WifiContract.DeviceEntry.COLUMN_MAC);
        String brand = values.getAsString(WifiContract.DeviceEntry.COLUMN_BRAND);
        String name = values.getAsString(WifiContract.DeviceEntry.COLUMN_NAME);

        assertEquals("Error with MAC: ", tMAC, MAC);
        assertEquals("Error with brand: ", tBrand, brand);
        assertEquals("Error with name: ", tName, name);
    }


    @Test
    public void TestInsertQueryConnected() throws Exception {

        testInsertQueryWifi();
        testInsertQueryDevice();

        Long wifiID = mWifiRepository.getWifiIDBySSID(tSSID);
        Long deviceID = mWifiRepository.getDeviceIDByMAC(tMAC);
        mWifiRepository.addConnection(wifiID, deviceID, tIP);

        ArrayList<ContentValues> connectedList = mWifiRepository.queryWifiConnections(wifiID);
        ContentValues value = connectedList.get(0);

        String ip = value.getAsString(WifiContract.WifiConnectDeviceEntry.COLUMN_IP);
        assertEquals("Error with IP: ", tIP, ip);

    }




}