package com.algalopez.mycon.wifi.domain.interactor;

import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;
import java.util.Date;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

public class WifiResponse {


    /* *********************************************************************************************
     * VARIABLES
     * *********************************************************************************************
     */


    private Date lastUpdate;

    private WifiEntity wifiInformation;

    private ArrayList<DeviceEntity> connectedDevices;


    /* *********************************************************************************************
     * GETTERS AND SETTERS
     * *********************************************************************************************
     */


    public Date getLastUpdate() { return lastUpdate; }

    public void setLastUpdate(Date lastUpdate) { this.lastUpdate = lastUpdate; }

    public WifiEntity getWifiInformation() { return wifiInformation; }

    public void setWifiInformation(WifiEntity wifiInformation) { this.wifiInformation = wifiInformation; }

    public ArrayList<DeviceEntity> getConnectedDevices() { return connectedDevices; }

    public void setConnectedDevices(ArrayList<DeviceEntity> connectedDevices) { this.connectedDevices = connectedDevices; }

    public void addConnectedDevice(DeviceEntity device){ this.connectedDevices.add((device)); }

    public void removeConnectedDevice(DeviceEntity device){ this.connectedDevices.remove(device); }



}
