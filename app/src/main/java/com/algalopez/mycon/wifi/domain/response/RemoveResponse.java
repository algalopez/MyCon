package com.algalopez.mycon.wifi.domain.response;

import com.algalopez.mycon.common.BaseResponse;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/19/17
 */

public class RemoveResponse extends BaseResponse {

    private static final String LOGTAG = "RemoveResponse";


    /* *********************************************************************************************
     * VARIABLES
     * *********************************************************************************************
     */


    private int removedRows = 0;
    private int removedWifi = 0;
    private int removedDevice = 0;


    /* *********************************************************************************************
     * CONSTRUCTOR
     * *********************************************************************************************
     */


    public RemoveResponse(){

        setProgress(0);
        setState(OK);
    }


    /* *********************************************************************************************
     * GETTERS AND SETTERS
     * *********************************************************************************************
     */

    public int getRemovedRows() { return removedRows; }

    public void setRemovedRows(int removedRows) { this.removedRows = removedRows; }

    public int getRemovedWifi() { return removedWifi; }

    public void setRemovedWifi(int removedWifi) { this.removedWifi = removedWifi; }

    public int getRemovedDevice() { return removedDevice; }

    public void setRemovedDevice(int removedDevice) { this.removedDevice = removedDevice; }


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
