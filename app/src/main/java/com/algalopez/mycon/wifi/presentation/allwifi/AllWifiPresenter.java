package com.algalopez.mycon.wifi.presentation.allwifi;

import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/12/17
 */

public class AllWifiPresenter {


    private IAllWifiView mView;
    private Executor mExecutor;
    private IWifiDbRepo mWifiDbRepo;

    AllWifiPresenter(Executor executor, IWifiDbRepo wifiDbRepo){
        this.mExecutor = executor;
        this.mWifiDbRepo = wifiDbRepo;
    }


    void attachView(IAllWifiView view){

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
