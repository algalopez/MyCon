package com.algalopez.mycon.wifi.presentation;

import com.algalopez.mycon.wifi.domain.interactor.WifiResponse;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;
import java.util.Date;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

class WifiPresenter {


    private IWifiView mView;
    private WifiResponse mWifiResponse;



    WifiPresenter(){

        mWifiResponse = new WifiResponse();
    }


    void attachView(IWifiView view){

        this.mView = view;
        mView.showWifiInfo(mWifiResponse.getWifiInformation().getSSID());
        mView.showConnectedDevices(mWifiResponse.getConnectedDevices());
    }


    void detachView(){

        this.mView = null;
    }


    String getState(){

        return mWifiResponse.storeInString();
    }


    void setState(String state){

        mWifiResponse.restoreFromString(state);
    }




    /* *********************************************************************************************
     * TESTS
     * *********************************************************************************************
     */


    void test1() {

        WifiEntity wifiInformation = new WifiEntity();

        wifiInformation.setSSID("WIFI_SSID");

        DeviceEntity dev1 = new DeviceEntity();
        DeviceEntity dev2 = new DeviceEntity();
        DeviceEntity dev3 = new DeviceEntity();
        DeviceEntity dev4 = new DeviceEntity();
        DeviceEntity dev5 = new DeviceEntity();

        dev1.setIP("IP1"); dev2.setIP("IP2"); dev3.setIP("IP3"); dev4.setIP("IP4"); dev5.setIP("IP5");
        dev1.setMAC("MAC1"); dev2.setMAC("MAC2"); dev3.setMAC("MAC3"); dev4.setMAC("MAC4"); dev5.setMAC("MAC5");
        dev1.setName("Name1"); dev2.setName("Name2"); dev3.setName("Name3"); dev4.setName("Name4"); dev5.setName("Name5");
        dev1.setBrand("Brand1"); dev2.setBrand("Brand2"); dev3.setBrand("Brand3"); dev4.setBrand("Brand4"); dev5.setBrand("Brand5");

        ArrayList<DeviceEntity> connectedDevices = new ArrayList<>();
        connectedDevices.add(dev1); connectedDevices.add(dev2); connectedDevices.add(dev3); connectedDevices.add(dev4); connectedDevices.add(dev5);

        mWifiResponse.setWifiInformation(wifiInformation);
        mWifiResponse.setConnectedDevices(connectedDevices);
        mWifiResponse.setLastUpdate(new Date());

        if (this.mView!= null){
            this.mView.showWifiInfo(mWifiResponse.getWifiInformation().getSSID());
            this.mView.showConnectedDevices(mWifiResponse.getConnectedDevices());
        }

    }

}
