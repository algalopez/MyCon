package com.algalopez.mycon.wifi.domain.response;

import com.algalopez.mycon.common.BaseResponse;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/12/17
 */

public class AllWifiResponse extends BaseResponse {

    private static final String LOGTAG = "AllWifiResponse";


    /* *********************************************************************************************
     * VARIABLES
     * *********************************************************************************************
     */


    private ArrayList<WifiEntity> mAllWifi;


    /* *********************************************************************************************
     * CONSTRUCTOR
     * *********************************************************************************************
     */


    public AllWifiResponse(){

        mAllWifi = new ArrayList<>();

        setProgress(0);
        setState(OK);
    }


    /* *********************************************************************************************
     * GETTERS AND SETTERS
     * *********************************************************************************************
     */


    public ArrayList<WifiEntity> getAllWifi(){ return mAllWifi; }


    public void addWifi(WifiEntity wifiEntity){ mAllWifi.add(wifiEntity); }


    public void setAllWifi(ArrayList<WifiEntity> allWifi){ mAllWifi = allWifi; }


    /* *********************************************************************************************
     * RESPONSE IMPLEMENTATION
     * *********************************************************************************************
     */


    @Override
    public String storeInString() {

        return null;
    }


    @Override
    public void restoreFromString(String s) {

    }
}
