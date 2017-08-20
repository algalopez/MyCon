package com.algalopez.mycon.wifi.data.manager;

import android.content.Context;
import android.net.DhcpInfo;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiInfo;
import android.util.Log;


import com.algalopez.mycon.wifi.data.manager.arp.ArpInfo;

import java.net.InetAddress;
import java.util.Locale;


/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/29/17
 */


// TODO: Add network to get device info from mac vendors;
// TODO: Check or REMOVE ips and dns formatting;


public class WifiManager implements IWifiManager{

    private static final String LOGTAG = "WifiManager";


    private android.net.wifi.WifiManager mWifimanager;
    private WifiInfo mWifiInfo;
    private DhcpInfo mDhcpInfo;

    private ArpInfo mArpInfo;

    public WifiManager(Context context){

        mWifimanager = (android.net.wifi.WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        mWifiInfo = mWifimanager.getConnectionInfo();
        mDhcpInfo = mWifimanager.getDhcpInfo();

        mArpInfo = new ArpInfo();
    }


    /* *********************************************************************************************
     * WIFI
     * *********************************************************************************************
     */

    @Override
    public boolean isWifiConnected(){

        if (mWifimanager.isWifiEnabled()){
            //Log.d(LOGTAG, "wifi is enabled");
            mWifiInfo = mWifimanager.getConnectionInfo();
            mDhcpInfo = mWifimanager.getDhcpInfo();

            if (mWifiInfo.getSupplicantState() == SupplicantState.COMPLETED && mWifiInfo.getNetworkId() >= 0) {

                //Log.d(LOGTAG, "Wifi is connected");
                return true;
            }
        }

        return false;
    }


    @Override
    public String getSSID(){

        // Log.d(LOGTAG, "SSID: " + mWifiInfo.getSSID());
        return mWifiInfo.getSSID();
    }


    @Override
    public String getBSSID(){

        return mWifiInfo.getBSSID();
    }


    @Override
    public String getIpAddress(){

        int ipaddr = mWifiInfo.getIpAddress();
        return String.format(Locale.UK, "%d.%d.%d.%d",
                (ipaddr & 0xff),
                (ipaddr >> 8 & 0xff),
                (ipaddr >> 16 & 0xff),
                (ipaddr >> 24 & 0xff));

    }


    @Override
    public boolean getHiddenSSID(){

        return mWifiInfo.getHiddenSSID();
    }


    @Override
    public int getRSSI(){

        return mWifiInfo.getRssi();
    }


    @Override
    public int getFrequency() {

        return mWifiInfo.getFrequency();
    }


    @Override
    public int getLinkSpeed() {

        return mWifiInfo.getLinkSpeed();
    }


    @Override
    public int getNetworkId(){

        return mWifiInfo.getNetworkId();
    }


    @Override
    public int getNetmask(){

        return mDhcpInfo.netmask;
    }


    @Override
    public String getServerAddress(){

        return "";
    }


    @Override
    public String getDNS1(){

        int dns1 = mDhcpInfo.dns1;

        if (dns1 < 0){
            dns1 = (~dns1) + 1;
        }

        return String.format(Locale.UK, "%d.%d.%d.%d",
                (dns1 & 0xff),
                (dns1 >> 8 & 0xff),
                (dns1 >> 16 & 0xff),
                (dns1 >> 24 & 0xff));
    }


    @Override
    public String getDNS2(){

        int dns2 = mDhcpInfo.dns2;

        if (dns2 < 0){
            dns2 = (~dns2) + 1;
        }

        return String.format(Locale.UK, "%d.%d.%d.%d",
                (dns2 & 0xff),
                (dns2 >> 8 & 0xff),
                (dns2 >> 16 & 0xff),
                (dns2 >> 24 & 0xff));
    }


    /* *********************************************************************************************
     * CONNECTED DEVICES
     * *********************************************************************************************
     */


    @Override
    public boolean isReachable(InetAddress addr){

        try {
            if (addr.isReachable(1000)) {

                Log.d(LOGTAG, String.valueOf("isReachable: " + addr.getCanonicalHostName()) + " (" + addr.toString() + ") is reachable");
                return true;
            } else {
                Log.d(LOGTAG, "isReachable: " + addr.toString() + " is not reachable");
                return false;
            }
        } catch (java.io.IOException ioException){

            Log.d(LOGTAG, "isReachable: Exception occured: " + ioException);
            return false;
        }
    }


    @Override
    public String getMAC(InetAddress addr){

        Log.d(LOGTAG, "getting MAC from ip: " + addr.getHostName());

        return mArpInfo.getMacFromArp(addr.getHostName());
    }


    @Override
    public String getName(InetAddress addr){

       return "";
    }


    @Override
    public String getBrand(InetAddress addr){

        return "";
    }


}
