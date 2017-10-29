package com.algalopez.mycon.wifi.data.manager;

import android.content.Context;
import android.util.Log;

import com.algalopez.mycon.wifi.data.manager.android.IWifiManager;
import com.algalopez.mycon.wifi.data.manager.android.WifiManager;
import com.algalopez.mycon.wifi.model.DeviceEntity;
import com.algalopez.mycon.wifi.model.WifiEntity;
import com.algalopez.mycon.wifi.domain.utils.WifiUtils;

import java.net.InetAddress;
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

        wifiEntity.setSSID(WifiUtils.removeDoubleQuotes(mWifiManager.getSSID()));
        wifiEntity.setLastUpdated(new Date());

        wifiEntity.setNetmask(mWifiManager.getNetmask());
        wifiEntity.setBSSID(mWifiManager.getBSSID());
        wifiEntity.setRSSI(mWifiManager.getRSSI());
        wifiEntity.setFrequency(mWifiManager.getFrequency());
        wifiEntity.setLinkSpeed(mWifiManager.getLinkSpeed());
        wifiEntity.setNetworkID(mWifiManager.getNetworkId());
        wifiEntity.setServerAddress(mWifiManager.getServerAddress());
        wifiEntity.setDns1(mWifiManager.getDNS1());
        wifiEntity.setDns2(mWifiManager.getDNS2());

        return wifiEntity;
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
            deviceEntity.setMAC(mWifiManager.getMAC(addr));
            deviceEntity.setName(mWifiManager.getName(addr));
            deviceEntity.setBrand(mWifiManager.getBrand(addr));
            return deviceEntity;
        } else {
            Log.d(LOGTAG, "getDevice: " + addrStr + " is not reachable");
        }
        return null;
    }


}
