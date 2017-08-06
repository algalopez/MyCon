package com.algalopez.mycon.wifi.domain.response;

import android.util.Log;

import com.algalopez.mycon.common.BaseResponse;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

public class WifiResponse extends BaseResponse {


    private static final String LOGTAG = "WifiResponse";

    public static final int ERROR_WIFI_NOT_CONNECTED = 1;
    public static final int ERROR_INVALID_WIFI = 2;
    public static final int ERROR_NEW_WIFI = 3;

    /* *********************************************************************************************
     * VARIABLES
     * *********************************************************************************************
     */


    private WifiEntity wifiInformation;

    private ArrayList<DeviceEntity> connectedDevices;


    /* *********************************************************************************************
     * CONSTRUCTOR
     * *********************************************************************************************
     */


    public WifiResponse(){

        wifiInformation = new WifiEntity();
        connectedDevices = new ArrayList<>();

        setProgress(0);
        setState(OK);
    }


    /* *********************************************************************************************
     * GETTERS AND SETTERS
     * *********************************************************************************************
     */


    public WifiEntity getWifiInformation() { return wifiInformation; }

    public void setWifiInformation(WifiEntity wifiInformation) { this.wifiInformation = wifiInformation; }

    public ArrayList<DeviceEntity> getConnectedDevices() { return connectedDevices; }

    public void setConnectedDevices(ArrayList<DeviceEntity> connectedDevices) { this.connectedDevices = connectedDevices; }

    public void addConnectedDevice(DeviceEntity device){ this.connectedDevices.add((device)); }

    public void removeConnectedDevice(DeviceEntity device){ this.connectedDevices.remove(device); }




    /* *********************************************************************************************
     * RESPONSE IMPLEMENTATION
     * *********************************************************************************************
     */


    private static final String LASTUPDATE = "LASTUPDATE";
    private static final String WIFI_SSID = "WIFI_SSID";
    private static final String DEVICE_IP = "DEVICE_IP";
    private static final String DEVICE_MAC = "DEVICE_MAC";
    private static final String DEVICE_BRAND = "DEVICE_BRAND";
    private static final String DEVICE_NAME = "DEVICE_NAME";
    private static final String CONNECTEDDEVICES = "ARRAY";

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", new Locale("uk"));
    //private DateFormat df = DateFormat.getDateTimeInstance();





    @Override
    public String storeInString() {

        JSONObject json = new JSONObject();
        try {

            // Store Wifi variables
            json.put(WIFI_SSID, wifiInformation.getSSID());

            // Store connected devices variables
            JSONArray jsonArray = new JSONArray();
            JSONObject jsonRow;
            for (DeviceEntity device: connectedDevices){
                jsonRow = new JSONObject();
                jsonRow.put(DEVICE_IP, device.getIP());
                jsonRow.put(DEVICE_MAC, device.getMAC());
                jsonRow.put(DEVICE_NAME, device.getName());
                jsonRow.put(DEVICE_BRAND, device.getBrand());
                jsonArray.put(jsonRow);
            }
            json.put(CONNECTEDDEVICES, jsonArray);

        } catch (JSONException e) {
            Log.e(LOGTAG, "storeInString: Error storing in json string: " + e);
            return "";
        }

        //Log.d(LOGTAG, "storeInString: " + json.toString());
        return json.toString();
    }


    @Override
    public void restoreFromString(String s) {
        //Log.d(LOGTAG, "restoreFromString: " + s);
        try {
            JSONObject json = new JSONObject(s);

            // Restore Wifi variables
            wifiInformation.setSSID(json.optString(WIFI_SSID, ""));

            // Restore device variables
            connectedDevices.clear();
            JSONArray jsonArray = json.getJSONArray(CONNECTEDDEVICES);
            JSONObject jsonRow;
            for(int i = 0; i < jsonArray.length(); i++){
                jsonRow = jsonArray.getJSONObject(i);
                DeviceEntity device = new DeviceEntity();
                device.setIP(jsonRow.optString(DEVICE_IP, ""));
                device.setName(jsonRow.optString(DEVICE_NAME, ""));
                device.setBrand(jsonRow.optString(DEVICE_BRAND, ""));
                device.setMAC(jsonRow.optString(DEVICE_MAC, ""));
                connectedDevices.add(device);
            }

        } catch (JSONException e) {
            Log.e(LOGTAG, "restoreFromString: Error restoring from string: " + e);
        }
    }


}
