package com.algalopez.mycon.wifi.model;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    10/29/17
 */

public class ConnectionEntity {

    private Long wifiID;
    private Long deviceID;
    private String IP;

    public ConnectionEntity() {
        this.wifiID = -1L;
        this.deviceID = -1L;
        this.IP = "";
    }


    public Long getWifiID() { return wifiID; }

    public void setWifiID(Long wifiID) { this.wifiID = wifiID; }

    public Long getDeviceID() { return deviceID; }

    public void setDeviceID(Long deviceID) { this.deviceID = deviceID; }

    public String getIP() { return IP; }

    public void setIP(String IP) { this.IP = IP; }


    @Override
    public String toString() {
        return "ConnectionEntity{" +
                "wifiID=" + wifiID +
                ", deviceID=" + deviceID +
                ", IP='" + IP + '\'' +
                '}';
    }
}


