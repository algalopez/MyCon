package com.algalopez.mycon.wifi.data.manager.arp;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/7/17
 */

public class ArpEntity {

    private String ipAddress;
    private String HWType;
    private String flags;
    private String HWAddress;
    private String mask;
    private String device;


    public String getIpAddress() { return ipAddress; }

    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

    public String getHWType() { return HWType; }

    public void setHWType(String HWType) { this.HWType = HWType; }

    public String getFlags() { return flags; }

    public void setFlags(String flags) { this.flags = flags; }

    public String getHWAddress() { return HWAddress; }

    public void setHWAddress(String HWAddress) { this.HWAddress = HWAddress; }

    public String getMask() { return mask; }

    public void setMask(String mask) { this.mask = mask; }

    public String getDevice() { return device; }

    public void setDevice(String device) { this.device = device; }
}
