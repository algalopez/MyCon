package com.algalopez.mycon.wifi.data;

import android.content.ContentValues;

import com.algalopez.mycon.wifi.data.database.WifiContract.WifiEntry;
import com.algalopez.mycon.wifi.data.database.WifiContract.DeviceEntry;
import com.algalopez.mycon.wifi.data.database.WifiContract.WifiConnectDeviceEntry;
import com.algalopez.mycon.wifi.data.database.WifiRepository;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/30/17
 */

public class WifiRepositoryDAO implements IWifiRepositoryDAO{


    private WifiRepository mLocal;

    public WifiRepositoryDAO(WifiRepository local){
        this.mLocal = local;
    }


    @Override
    public ArrayList<DeviceEntity> getConnectedDevices(Long wifiID) {

        DeviceEntity newEntity;
        ArrayList<DeviceEntity> connectedDevices = new ArrayList<>();

        ArrayList<ContentValues> valuesList = mLocal.queryWifiConnections(wifiID);
        for (ContentValues values: valuesList){
            newEntity = new DeviceEntity();
            newEntity.setIP(values.getAsString(WifiConnectDeviceEntry.COLUMN_IP));
            newEntity.setID(values.getAsLong(WifiConnectDeviceEntry._ID));
            connectedDevices.add(newEntity);
        }


        for (DeviceEntity deviceEntity: connectedDevices){

            ContentValues values = mLocal.queryDevice(deviceEntity.getID());
            deviceEntity.setMAC(values.getAsString(DeviceEntry.COLUMN_MAC));
            deviceEntity.setName(values.getAsString(DeviceEntry.COLUMN_NAME));
            deviceEntity.setBrand(values.getAsString(DeviceEntry.COLUMN_BRAND));
        }

        return connectedDevices;
    }


    @Override
    public WifiEntity getWifi(Long wifiID) {

        WifiEntity newEntity;
        ContentValues values = mLocal.queryWifi(wifiID);

        // TODO: add -> bool wifiExist(Long wifiID)
        if (values == null || values.size() == 0){
            return null;
        }

        newEntity = new WifiEntity();
        newEntity.setID(wifiID);
        newEntity.setSSID(values.getAsString(WifiEntry.COLUMN_SSID));

        return newEntity;
    }


    @Override
    public Long storeWifi(WifiEntity newWifiEntity) {



        return null;
    }


    @Override
    public int removeWifi(Long wifiID) {

        return 0;
    }


    @Override
    public int updateWifi(Long wifiID, WifiEntity newWifiEntity) {

        return 0;
    }


    @Override
    public DeviceEntity getDevice(Long deviceID) {

        return null;
    }


    @Override
    public Long storeDevice(DeviceEntity newDeviceEntity) {

        return null;
    }


    @Override
    public int removeDevice(Long deviceID) {

        return 0;
    }


    @Override
    public int updateDevice(Long deviceID, DeviceEntity newDeviceEntity) {

        return 0;
    }


    @Override
    public ArrayList<WifiEntity> getAllWifi() {

        return null;
    }


    @Override
    public ArrayList<DeviceEntity> getAllDevices() {

        return null;
    }


    @Override
    public int removeAllWifi() {

        return 0;
    }


    @Override
    public int removeAllDevices() {

        return 0;
    }
}
