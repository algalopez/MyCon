package com.algalopez.mycon.wifi.domain.model;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

public class WifiEntity {


    private String SSID;

    public WifiEntity(){

        this.SSID = "";
    }

    public String getSSID() { return SSID; }

    public void setSSID(String SSID) { this.SSID = SSID; }

}
