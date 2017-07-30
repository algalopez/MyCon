package com.algalopez.mycon.wifi.domain.model;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

public class WifiEntity {


    private Long ID;
    private String SSID;

    public WifiEntity(){

        this.ID = -1L;
        this.SSID = "";
    }

    public Long getID() { return ID; }

    public void setID(Long ID) { this.ID = ID; }

    public String getSSID() { return SSID; }

    public void setSSID(String SSID) { this.SSID = SSID; }

}
