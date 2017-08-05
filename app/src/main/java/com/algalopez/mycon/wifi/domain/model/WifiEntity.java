package com.algalopez.mycon.wifi.domain.model;

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

    public WifiEntity(){

        this.ID = -1L;
        this.SSID = "";
        this.lastUpdated = new Date();
        this.password = "";
    }

    public Long getID() { return ID; }

    public void setID(Long ID) { this.ID = ID; }

    public String getSSID() { return SSID; }

    public void setSSID(String SSID) { this.SSID = SSID; }

    public Date getLastUpdated() { return lastUpdated; }

    public void setLastUpdated(Date lastUpdated) { this.lastUpdated = lastUpdated; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }


}
