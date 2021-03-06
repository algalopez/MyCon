package com.algalopez.mycon.wifi.data.manager.android;

import java.net.InetAddress;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/5/17
 */

public interface IWifiManager {




    /* *********************************************************************************************
     * WIFI
     * *********************************************************************************************
     */


    boolean isWifiConnected();

    String getSSID();

    String getBSSID();

    String getIpAddress();

    int getRSSI();

    int getFrequency();

    int getLinkSpeed();

    int getNetworkId();

    String getNetmask();

    String getServerAddress();

    String getDNS1();

    String getDNS2();


    /* *********************************************************************************************
     * CONNECTED DEVICES
     * *********************************************************************************************
     */


    boolean isReachable(InetAddress addr);

    String getMAC(InetAddress addr);

    String getName(InetAddress addr);

    String getBrand(InetAddress addr);



}
