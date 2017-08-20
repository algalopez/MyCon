package com.algalopez.mycon.wifi.presentation.detaildevice;

import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.presentation.detailwifi.DetailWifiPresenter;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/12/17
 */

public class DetailDevicePresenter {


    private IDetailDeviceView mView;
    private Executor mExecutor;
    private IWifiDbRepo mWifiDbRepo;

    DetailDevicePresenter(Executor executor, IWifiDbRepo wifiDbRepo){

        this.mExecutor = executor;
        this.mWifiDbRepo = wifiDbRepo;
    }


    void attachView(IDetailDeviceView view){

        this.mView = view;
    }


    void detachView(){

        this.mView = null;
    }


    String getState(){

        return "";
    }


    void setState(String state){

    }


    /* *********************************************************************************************
     * DOMAIN ACTIONS
     * *********************************************************************************************
     */



    /* *********************************************************************************************
     * VIEW ACTIONS
     * *********************************************************************************************
     */



    /* *********************************************************************************************
     * CALLBACKS
     * *********************************************************************************************
     */


}
