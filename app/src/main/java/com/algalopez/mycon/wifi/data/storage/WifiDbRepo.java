package com.algalopez.mycon.wifi.data.storage;

import android.content.ContentValues;
import android.content.Context;

import com.algalopez.mycon.wifi.data.storage.database.ConnectedDatabase;
import com.algalopez.mycon.wifi.data.storage.database.DeviceDatabase;
import com.algalopez.mycon.wifi.data.storage.database.IConnectedDatabase;
import com.algalopez.mycon.wifi.data.storage.database.IDeviceDatabase;
import com.algalopez.mycon.wifi.data.storage.database.IWifiDatabase;
import com.algalopez.mycon.wifi.data.storage.database.contract.WifiContract.WifiEntry;
import com.algalopez.mycon.wifi.data.storage.database.contract.WifiContract.DeviceEntry;
import com.algalopez.mycon.wifi.data.storage.database.contract.WifiContract.WifiConnectDeviceEntry;
import com.algalopez.mycon.wifi.data.storage.database.WifiDatabase;
import com.algalopez.mycon.wifi.model.ConnectionEntity;
import com.algalopez.mycon.wifi.model.DeviceEntity;
import com.algalopez.mycon.wifi.model.WifiEntity;

import java.util.ArrayList;
import java.util.Date;


/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/30/17
 */


public class WifiDbRepo implements IWifiDbRepo {

    //private static final String LOGTAG = "WifiDbRepo";

    //Context mContext;

    private IWifiDatabase mWifiDatabase;
    private IDeviceDatabase mDeviceDatabase;
    private IConnectedDatabase mConnectedDatabase;


    public WifiDbRepo(Context context){

        this.mWifiDatabase = new WifiDatabase(context);
        this.mDeviceDatabase = new DeviceDatabase(context);
        this.mConnectedDatabase = new ConnectedDatabase(context);
    }


    /* *********************************************************************************************
     * WIFI
     * *********************************************************************************************
     */


    @Override
    public WifiEntity getWifi(Long wifiID) {

        WifiEntity newEntity;
        ContentValues values = mWifiDatabase.queryWifi(wifiID);

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

        return mWifiDatabase.insertWifi(values);
    }


    @Override
    public int removeWifi(Long wifiID) {

        return mWifiDatabase.deleteWifi(wifiID);
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

        return mWifiDatabase.updateWifi(values);
    }


    @Override
    public ArrayList<WifiEntity> getAllWifi() {

        ArrayList<ContentValues> valuesList= mWifiDatabase.queryAllWifi();
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
    public int removeAllWifi() {

        return mWifiDatabase.deleteAllWifi();
    }


    @Override
    public Long getWifiIDBySSID(String SSID) {

        return mWifiDatabase.getWifiIDBySSID(SSID);
    }


    /* *********************************************************************************************
     * DEVICE
     * *********************************************************************************************
     */


    @Override
    public DeviceEntity getDevice(Long deviceID) {

        DeviceEntity newEntity;
        ContentValues values = mDeviceDatabase.queryDevice(deviceID);

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

        return mDeviceDatabase.insertDevice(values);
    }


    @Override
    public int removeDevice(Long deviceID) {

        return mDeviceDatabase.deleteDevice(deviceID);
    }


    @Override
    public int updateDevice(Long deviceID, DeviceEntity newDeviceEntity) {

        ContentValues values = new ContentValues();
        values.put(DeviceEntry._ID, newDeviceEntity.getID());
        values.put(DeviceEntry.COLUMN_MAC, newDeviceEntity.getMAC());
        values.put(DeviceEntry.COLUMN_NAME, newDeviceEntity.getName());
        values.put(DeviceEntry.COLUMN_BRAND, newDeviceEntity.getBrand());

        return mDeviceDatabase.updateDevice(values);
    }


    @Override
    public ArrayList<DeviceEntity> getAllDevices() {

        ArrayList<ContentValues> valuesList= mDeviceDatabase.queryAllDevice();
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

        return mDeviceDatabase.deleteAllDevice();
    }


    @Override
    public Long getDeviceIDByMAC(String Mac) {

        return mDeviceDatabase.getDeviceIDByMAC(Mac);
    }


    /* *********************************************************************************************
     * CONNECTED DEVICES
     * *********************************************************************************************
     */


    @Override
    public ArrayList<ConnectionEntity> getConnectedDevices(Long wifiID) {

        ConnectionEntity newEntity;
        ArrayList<ConnectionEntity> connectedDevices = new ArrayList<>();

        ArrayList<ContentValues> valuesList = mConnectedDatabase.queryWifiConnections(wifiID);
        for (ContentValues values: valuesList){
            newEntity = new ConnectionEntity();
            newEntity.setWifiID(wifiID);
            newEntity.setDeviceID(values.getAsLong(WifiConnectDeviceEntry.COLUMN_DEVICE));
            newEntity.setIP(values.getAsString(WifiConnectDeviceEntry.COLUMN_IP));
            connectedDevices.add(newEntity);
        }

        return connectedDevices;
    }


    @Override
    public int removeConnectedDevices(Long wifiID) {

        return mConnectedDatabase.deleteAllConnectedDevices(wifiID);
    }


    @Override
    public int storeConnectedDevices(Long wifiID, ArrayList<ConnectionEntity> connectionEntities) {

        ContentValues values;
        for (ConnectionEntity connectionEntity: connectionEntities){
            values = new ContentValues();
            values.put(WifiConnectDeviceEntry.COLUMN_WIFI, wifiID);
            values.put(WifiConnectDeviceEntry.COLUMN_DEVICE, connectionEntity.getDeviceID());
            values.put(WifiConnectDeviceEntry.COLUMN_IP, connectionEntity.getIP());
            mConnectedDatabase.insertConnection(values);
        }

        return 0;
    }
}
