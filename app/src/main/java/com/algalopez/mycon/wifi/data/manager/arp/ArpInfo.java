package com.algalopez.mycon.wifi.data.manager.arp;

import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/7/17
 */




public class ArpInfo {

    private static final String LOGTAG = "ArpInfo";

    private static final int COLUMN_IP = 0;
    private static final int COLUMN_HWTYPE = 1;
    private static final int COLUMN_FLAGS = 2;
    private static final int COLUMN_HWADDRESS = 3;
    private static final int COLUMN_MASK = 4;
    private static final int COLUMN_DEVICE = 5;

    private boolean isArpRead = false;

    private HashMap<String, ArpEntity> mIpArpMapping;

    public ArpInfo(){
        mIpArpMapping = new HashMap<>();
    }


    public boolean readArp(){

        isArpRead = true;

        BufferedReader bufferedReader;
        ArpEntity arpEntity;

        try {

            bufferedReader = new BufferedReader(new FileReader("/proc/net/arp"));
            String line;


            while ((line = bufferedReader.readLine()) != null) {

                Log.d(LOGTAG, "line: " + line);

                String[] splitted = line.split(" +");

                if (splitted.length == 6) {

                    arpEntity = new ArpEntity();
                    arpEntity.setIpAddress(splitted[COLUMN_IP]);
                    arpEntity.setHWType(splitted[COLUMN_HWTYPE]);
                    arpEntity.setFlags(splitted[COLUMN_FLAGS]);
                    arpEntity.setHWAddress(splitted[COLUMN_HWADDRESS]);
                    arpEntity.setMask(splitted[COLUMN_MASK]);
                    arpEntity.setDevice(splitted[COLUMN_DEVICE]);
                    mIpArpMapping.put(splitted[COLUMN_IP], arpEntity);
                }
            }

            bufferedReader.close();

        } catch (java.io.FileNotFoundException e1){

            Log.d(LOGTAG, "Error: /proc/net/arp not found -> " + e1);
            return false;
        } catch (java.io.IOException e2) {

            Log.d(LOGTAG, "Error: IOException -> " + e2);
            return false;
        }

        return true;
    }


    public String getMacFromArp(String ip){

        if (!isArpRead){
            readArp();
        }

        if (mIpArpMapping.containsKey(ip)) {
            return mIpArpMapping.get(ip).getHWAddress();
        }

        return "";
    }



}
