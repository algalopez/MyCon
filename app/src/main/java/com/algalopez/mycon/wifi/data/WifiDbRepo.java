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

    //private static final String LOGTAG = "WifiDbRepo";

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

        newEntity.setNetmask(values.getAsString(WifiEntry.COLUMN_NETMASK));
        newEntity.setBSSID(values.getAsString(WifiEntry.COLUMN_BSSID));
        newEntity.setRSSI(values.getAsInteger(WifiEntry.COLUMN_RSSI));
        newEntity.setFrequency(values.getAsInteger(WifiEntry.COLUMN_FREQUENCY));
        newEntity.setLinkSpeed(values.getAsInteger(WifiEntry.COLUMN_LINKSPEED));
        newEntity.setNetworkID(values.getAsInteger(WifiEntry.COLUMN_NETWORKID));
        newEntity.setServerAddress(values.getAsString(WifiEntry.COLUMN_SERVERADDRESS));
        newEntity.setDns1(values.getAsString(WifiEntry.COLUMN_DNS1));
        newEntity.setDns2(values.getAsString(WifiEntry.COLUMN_DNS2));

        return newEntity;
    }


    @Override
    public Long storeWifi(WifiEntity newWifiEntity) {

        ContentValues values = new ContentValues();
        //values.put(WifiEntry._ID, newWifiEntity.getID());
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

        values.put(WifiEntry.COLUMN_NETMASK, newWifiEntity.getNetmask());
        values.put(WifiEntry.COLUMN_BSSID, newWifiEntity.getBSSID());
        values.put(WifiEntry.COLUMN_RSSI, newWifiEntity.getRSSI());
        values.put(WifiEntry.COLUMN_FREQUENCY, newWifiEntity.getFrequency());
        values.put(WifiEntry.COLUMN_LINKSPEED, newWifiEntity.getLinkSpeed());
        values.put(WifiEntry.COLUMN_NETWORKID, newWifiEntity.getNetworkID());
        values.put(WifiEntry.COLUMN_SERVERADDRESS, newWifiEntity.getServerAddress());
        values.put(WifiEntry.COLUMN_DNS1, newWifiEntity.getDns1());
        values.put(WifiEntry.COLUMN_DNS2, newWifiEntity.getDns2());

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

            wifiEntity.setNetmask(values.getAsString(WifiEntry.COLUMN_NETMASK));
            wifiEntity.setBSSID(values.getAsString(WifiEntry.COLUMN_BSSID));
            wifiEntity.setRSSI(values.getAsInteger(WifiEntry.COLUMN_RSSI));
            wifiEntity.setFrequency(values.getAsInteger(WifiEntry.COLUMN_FREQUENCY));
            wifiEntity.setLinkSpeed(values.getAsInteger(WifiEntry.COLUMN_LINKSPEED));
            wifiEntity.setNetworkID(values.getAsInteger(WifiEntry.COLUMN_NETWORKID));
            wifiEntity.setServerAddress(values.getAsString(WifiEntry.COLUMN_SERVERADDRESS));
            wifiEntity.setDns1(values.getAsString(WifiEntry.COLUMN_DNS1));
            wifiEntity.setDns2(values.getAsString(WifiEntry.COLUMN_DNS2));
            wifiEntityList.add(wifiEntity);
        }

        return wifiEntityList;
    }


    @Override
    public int removeAll() {

        return mDatabase.deleteAll();
    }

    @Override
    public Long getWifiIDBySSID(String SSID) {

        return mDatabase.getWifiIDBySSID(SSID);
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
        //values.put(DeviceEntry._ID, newDeviceEntity.getID());
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


    @Override
    public Long getDeviceIDByMAC(String Mac) {

        return mDatabase.getDeviceIDByMAC(Mac);
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
            newEntity.setID(values.getAsLong(WifiConnectDeviceEntry.COLUMN_DEVICE));
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


    @Override
    public int storeConnectedDevices(Long wifiID, ArrayList<DeviceEntity> deviceEntities) {

        ContentValues values;
        for (DeviceEntity deviceEntity: deviceEntities){
            values = new ContentValues();
            values.put(WifiConnectDeviceEntry.COLUMN_WIFI, wifiID);
            values.put(WifiConnectDeviceEntry.COLUMN_DEVICE, deviceEntity.getID());
            values.put(WifiConnectDeviceEntry.COLUMN_IP, deviceEntity.getIP());
            mDatabase.insertConnection(values);
        }

        return 0;
    }
}
