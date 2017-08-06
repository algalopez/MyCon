package com.algalopez.mycon.wifi.data;

import android.content.Context;
import android.util.Log;

import com.algalopez.mycon.wifi.data.manager.IWifiManager;
import com.algalopez.mycon.wifi.data.manager.WifiManager;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/5/17
 */

public class WifiManagerRepo implements IWifiManagerRepo {


    private static final String LOGTAG = "WifiManagerRepo";


    private IWifiManager mWifiManager;


    public WifiManagerRepo(Context context){

        this.mWifiManager = new WifiManager(context);
    }


    @Override
    public boolean isWifiConnected() {

        return mWifiManager.isWifiConnected();
    }


    @Override
    public WifiEntity getWifi() {


        WifiEntity wifiEntity = new WifiEntity();

        wifiEntity.setSSID(mWifiManager.getSSID());
        wifiEntity.setLastUpdated(new Date());

        return null;
    }


    @Override
    public DeviceEntity getDevice(String addrStr) {

        InetAddress addr;
        DeviceEntity deviceEntity;


        try {
            addr = InetAddress.getByName(addrStr);
        } catch (java.net.UnknownHostException unknownHostException) {

            Log.d(LOGTAG, "getDevice: Unknown host exception " + unknownHostException);
            return null;
        }


        if (mWifiManager.isReachable(addr)) {

            deviceEntity = new DeviceEntity();
            deviceEntity.setIP(addrStr);
            deviceEntity.setMAC(mWifiManager.getMAC(addr));
            deviceEntity.setName(mWifiManager.getName(addr));
            deviceEntity.setBrand(mWifiManager.getBrand(addr));
            return deviceEntity;
        } else {
            Log.d(LOGTAG, "getDevice: " + addrStr + " is not reachable");
        }
        return null;
    }


//    @Override
//    public ArrayList<DeviceEntity> getAllDevices() {
//        return null;
//    }



}