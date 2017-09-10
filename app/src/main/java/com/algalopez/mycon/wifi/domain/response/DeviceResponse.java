package com.algalopez.mycon.wifi.domain.response;

import com.algalopez.mycon.common.BaseResponse;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    9/10/17
 */

public class DeviceResponse extends BaseResponse {


    private static final String LOGTAG = "DeviceResponse";

    public static final int ERROR_INVALID_DEVICE = 1;


    /* *********************************************************************************************
     * VARIABLES
     * *********************************************************************************************
     */


    private DeviceEntity deviceInformation;


    /* *********************************************************************************************
     * CONSTRUCTOR
     * *********************************************************************************************
     */


    public DeviceResponse(){

        deviceInformation = new DeviceEntity();

        setProgress(0);
        setState(OK);
    }


    /* *********************************************************************************************
     * GETTERS AND SETTERS
     * *********************************************************************************************
     */


    public DeviceEntity getDeviceInformation() {

        return this.deviceInformation;
    }


    public void setDeviceInformation(DeviceEntity device) {

        this.deviceInformation = device;
    }


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
