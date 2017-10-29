package com.algalopez.mycon.wifi.model;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

public class DeviceEntity {


    private Long ID;
    private String MAC;
    private String name;
    private String brand;

    public DeviceEntity(){
        this.ID = -1L;
        this.MAC = "";
        this.name = "";
        this.brand = "";
    }

    public Long getID() { return ID; }

    public void setID(Long ID) { this.ID = ID; }

    public String getMAC() { return MAC; }

    public void setMAC(String MAC) { this.MAC = MAC; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getBrand() { return brand; }

    public void setBrand(String brand) { this.brand = brand; }


    @Override
    public String toString() {
        return "DeviceEntity{" +
                "ID=" + ID +
                ", MAC='" + MAC + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                '}';
    }
}
