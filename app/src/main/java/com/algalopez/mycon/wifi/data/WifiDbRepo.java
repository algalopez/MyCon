package com.algalopez.mycon.wifi.data;

import android.content.ContentValues;
import android.content.Context;

import com.algalopez.mycon.wifi.data.storage.IWifiStorage;
import com.algalopez.mycon.wifi.data.storage.database.WifiContract.WifiEntry;
import com.algalopez.mycon.wifi.data.storage.database.WifiContract.DeviceEntry;
import com.algalopez.mycon.wifi.data.storage.database.WifiContract.WifiConnectDeviceEntry;
import com.algalopez.mycon.wifi.data.storage.database.WifiDatabase;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;
import java.util.Date;


/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/30/17
 */


public class WifiDbRepo implements IWifiDbRepo {

    private static final String LOGTAG = "WifiDbRepo";

    private IWifiStorage mDatabase;


    public WifiDbRepo(Context context){

        this.mDatabase = new WifiDatabase(context);
    }


    /* *********************************************************************************************
     * WIFI
     * *********************************************************************************************
     */


    @Override
    public WifiEntity getWifi(Long wifiID) {

        WifiEntity newEntity;
        ContentValues values = mDatabase.queryWifi(wifiID);

        if (values == null || values.size() == 0){
            return null;
        }

        newEntity = new WifiEntity();
        newEntity.setID(wifiID);
        newEntity.setSSID(values.getAsString(WifiEntry.COLUMN_SSID));
        newEntity.setPassword(values.getAsString(WifiEntry.COLUMN_PASSWORD));
        newEntity.setLastUpdated(new Date(values.getAsLong(WifiEntry.COLUMN_LASTUPDATED)));

        return newEntity;
    }


    @Override
    public Long storeWifi(WifiEntity newWifiEntity) {

        ContentValues values = new ContentValues();
        values.put(WifiEntry._ID, newWifiEntity.getID());
        values.put(WifiEntry.COLUMN_SSID, newWifiEntity.getSSID());
        values.put(WifiEntry.COLUMN_PASSWORD, newWifiEntity.getPassword());
        values.put(WifiEntry.COLUMN_LASTUPDATED, newWifiEntity.getLastUpdated().getTime());

        return mDatabase.insertWifi(values);
    }


    @Override
    public int removeWifi(Long wifiID) {

        return mDatabase.deleteWifi(wifiID);
    }


    @Override
    public int updateWifi(WifiEntity newWifiEntity) {

        ContentValues values = new ContentValues();
        values.put(WifiEntry._ID, newWifiEntity.getID());
        values.put(WifiEntry.COLUMN_SSID, newWifiEntity.getSSID());
        values.put(WifiEntry.COLUMN_PASSWORD, newWifiEntity.getPassword());
        values.put(WifiEntry.COLUMN_LASTUPDATED, newWifiEntity.getLastUpdated().getTime());

        return mDatabase.updateWifi(values);
    }


    @Override
    public ArrayList<WifiEntity> getAllWifi() {

        ArrayList<ContentValues> valuesList= mDatabase.queryAllWifi();
        ArrayList<WifiEntity> wifiEntityList = new ArrayList<>();
        WifiEntity wifiEntity;

        for (ContentValues values: valuesList){
            wifiEntity = new WifiEntity();
            wifiEntity.setID(values.getAsLong(WifiEntry.COLUMN_SSID));
            wifiEntity.setSSID(values.getAsString(WifiEntry.COLUMN_SSID));
            wifiEntity.setPassword(values.getAsString(WifiEntry.COLUMN_PASSWORD));
            Date lastUpdate = new Date(values.getAsLong(WifiEntry.COLUMN_LASTUPDATED));
            wifiEntity.setLastUpdated(lastUpdate);
            wifiEntityList.add(wifiEntity);
        }

        return wifiEntityList;
    }


    @Override
    public int removeAll() {

        return mDatabase.deleteAll();
    }


    /* *********************************************************************************************
     * DEVICE
     * *********************************************************************************************
     */


    @Override
    public DeviceEntity getDevice(Long deviceID) {

        DeviceEntity newEntity;
        ContentValues values = mDatabase.queryDevice(deviceID);

        if (values == null || values.size() == 0){
            return null;
        }

        newEntity = new DeviceEntity();
        newEntity.setID(deviceID);
        newEntity.setMAC(values.getAsString(DeviceEntry.COLUMN_MAC));
        newEntity.setName(values.getAsString(DeviceEntry.COLUMN_NAME));
        newEntity.setBrand(values.getAsString(DeviceEntry.COLUMN_BRAND));

        return newEntity;
    }


    @Override
    public Long storeDevice(DeviceEntity newDeviceEntity) {

        ContentValues values = new ContentValues();
        values.put(DeviceEntry._ID, newDeviceEntity.getID());
        values.put(DeviceEntry.COLUMN_MAC, newDeviceEntity.getMAC());
        values.put(DeviceEntry.COLUMN_NAME, newDeviceEntity.getName());
        values.put(DeviceEntry.COLUMN_BRAND, newDeviceEntity.getBrand());

        return mDatabase.insertDevice(values);
    }


    @Override
    public int removeDevice(Long deviceID) {

        return mDatabase.deleteDevice(deviceID);
    }


    @Override
    public int updateDevice(Long deviceID, DeviceEntity newDeviceEntity) {

        ContentValues values = new ContentValues();
        values.put(DeviceEntry._ID, newDeviceEntity.getID());
        values.put(DeviceEntry.COLUMN_MAC, newDeviceEntity.getMAC());
        values.put(DeviceEntry.COLUMN_NAME, newDeviceEntity.getName());
        values.put(DeviceEntry.COLUMN_BRAND, newDeviceEntity.getBrand());

        return mDatabase.updateDevice(values);
    }


    @Override
    public ArrayList<DeviceEntity> getAllDevices() {

        ArrayList<ContentValues> valuesList= mDatabase.queryAllDevice();
        ArrayList<DeviceEntity> deviceEntityList = new ArrayList<>();
        DeviceEntity deviceEntity;

        for (ContentValues values: valuesList){
            deviceEntity = new DeviceEntity();
            deviceEntity.setID(values.getAsLong(DeviceEntry._ID));
            deviceEntity.setMAC(values.getAsString(DeviceEntry.COLUMN_MAC));
            deviceEntity.setName(values.getAsString(DeviceEntry.COLUMN_NAME));
            deviceEntity.setBrand(values.getAsString(DeviceEntry.COLUMN_BRAND));
            deviceEntityList.add(deviceEntity);
        }

        return deviceEntityList;
    }


    @Override
    public int removeAllDevices() {

        return mDatabase.deleteAllDevice();
    }


    /* *********************************************************************************************
     * CONNECTED DEVICES
     * *********************************************************************************************
     */


    @Override
    public ArrayList<DeviceEntity> getConnectedDevices(Long wifiID) {

        DeviceEntity newEntity;
        ArrayList<DeviceEntity> connectedDevices = new ArrayList<>();

        ArrayList<ContentValues> valuesList = mDatabase.queryWifiConnections(wifiID);
        for (ContentValues values: valuesList){
            newEntity = new DeviceEntity();
            newEntity.setIP(values.getAsString(WifiConnectDeviceEntry.COLUMN_IP));
            newEntity.setID(values.getAsLong(WifiConnectDeviceEntry._ID));
            connectedDevices.add(newEntity);
        }


        for (DeviceEntity deviceEntity: connectedDevices){

            ContentValues values = mDatabase.queryDevice(deviceEntity.getID());
            deviceEntity.setMAC(values.getAsString(DeviceEntry.COLUMN_MAC));
            deviceEntity.setName(values.getAsString(DeviceEntry.COLUMN_NAME));
            deviceEntity.setBrand(values.getAsString(DeviceEntry.COLUMN_BRAND));
        }

        return connectedDevices;
    }


    @Override
    public int removeConnectedDevices(Long wifiID) {

        return mDatabase.deleteAllConnectedDevices(wifiID);
    }
}
