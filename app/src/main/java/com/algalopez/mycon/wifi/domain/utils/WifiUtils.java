package com.algalopez.mycon.wifi.domain.utils;

import android.util.Log;

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


    public static String removeDoubleQuotes(String s){
        s = s.replaceFirst("^\"", ""); // ^ Beginning of the line
        s = s.replaceFirst("\"$", ""); // $ End of the line
        return s;
    }


    public static String getNetworkPrefix(WifiEntity wifiEntity){

        String sa = wifiEntity.getServerAddress();
        String[] parts = sa.split("\\.");

        if (parts.length != 4){

            return "";
        }

        return parts[0] + "." + parts[1] + "." + parts[2] + ".";
    }
}
