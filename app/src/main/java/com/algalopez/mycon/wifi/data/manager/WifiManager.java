package com.algalopez.mycon.wifi.data.manager;

import android.content.Context;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;

import com.algalopez.mycon.wifi.domain.model.WifiEntity;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/29/17
 */

public class WifiManager {

    private android.net.wifi.WifiManager mWifimanager;
    private WifiInfo mWifiInfo;

    public WifiManager(Context context){
        mWifimanager = (android.net.wifi.WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mWifiInfo = mWifimanager.getConnectionInfo();
    }

    public boolean isWifiActive(){

        return (mWifiInfo != null);
    }


    public String getWifiSSID(){

        return mWifiInfo.getSSID();
    }



}
