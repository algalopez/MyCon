package com.algalopez.mycon.wifi.model;

import java.util.Date;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

public class WifiEntity {




    private Long ID;
    private String SSID;
    private Date lastUpdated;
    private String password;
    private String netmask;
    private String BSSID;
    private int RSSI;
    private int frequency;
    private int linkSpeed;
    private int networkID;
    private String serverAddress;
    private String dns1;
    private String dns2;

    public WifiEntity(){

        this.ID = -1L;
    }

    public Long getID() { return ID; }

    public void setID(Long ID) { this.ID = ID; }

    public String getSSID() { return SSID; }

    public void setSSID(String SSID) { this.SSID = SSID; }

    public Date getLastUpdated() { return lastUpdated; }

    public void setLastUpdated(Date lastUpdated) { this.lastUpdated = lastUpdated; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getNetmask() { return netmask; }

    public void setNetmask(String netmask) { this.netmask = netmask; }

    public String getBSSID() { return BSSID; }

    public void setBSSID(String BSSID) { this.BSSID = BSSID; }

    public int getRSSI() { return RSSI; }

    public void setRSSI(int RSSI) { this.RSSI = RSSI; }

    public int getFrequency() { return frequency; }

    public void setFrequency(int frequency) { this.frequency = frequency; }

    public int getLinkSpeed() { return linkSpeed; }

    public void setLinkSpeed(int linkSpeed) { this.linkSpeed = linkSpeed; }

    public int getNetworkID() { return networkID; }

    public void setNetworkID(int networkID) { this.networkID = networkID; }

    public String getServerAddress() { return serverAddress; }

    public void setServerAddress(String serverAddress) { this.serverAddress = serverAddress; }

    public String getDns1() { return dns1; }

    public void setDns1(String dns1) { this.dns1 = dns1; }

    public String getDns2() { return dns2; }

    public void setDns2(String dns2) { this.dns2 = dns2; }


    @Override
    public String toString() {
        return "WifiEntity{" +
                "ID=" + ID +
                ", SSID='" + SSID + '\'' +
                ", lastUpdated=" + lastUpdated +
                ", password='" + password + '\'' +
                ", netmask='" + netmask + '\'' +
                ", BSSID='" + BSSID + '\'' +
                ", RSSI=" + RSSI +
                ", frequency=" + frequency +
                ", linkSpeed=" + linkSpeed +
                ", networkID=" + networkID +
                ", serverAddress='" + serverAddress + '\'' +
                ", dns1='" + dns1 + '\'' +
                ", dns2='" + dns2 + '\'' +
                '}';
    }
}
