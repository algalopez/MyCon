package com.algalopez.mycon.wifi.domain.model;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

public class DeviceEntity {


    private Long ID;
    private String IP;
    private String MAC;
    private String name;
    private String brand;

    public DeviceEntity(){
        this.ID = -1L;
        this.IP = "";
        this.MAC = "";
        this.name = "";
        this.brand = "";
    }

    public Long getID() { return ID; }

    public void setID(Long ID) { this.ID = ID; }

    public String getIP() { return IP; }

    public void setIP(String IP) { this.IP = IP; }

    public String getMAC() { return MAC; }

    public void setMAC(String MAC) { this.MAC = MAC; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }
}
