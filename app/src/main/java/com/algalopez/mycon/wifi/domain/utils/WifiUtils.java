package com.algalopez.mycon.wifi.domain.utils;

import com.algalopez.mycon.wifi.domain.model.WifiEntity;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/6/17
 */


// TODO: This
public class WifiUtils {


    public static boolean isValidIP4(WifiEntity wifiEntity){

        return !wifiEntity.getSSID().equals("");
    }



}
