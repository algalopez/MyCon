package com.algalopez.mycon.wifi.presentation;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.domain.interactor.WifiActor;
import com.algalopez.mycon.wifi.domain.interactor.WifiResponse;


/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */


class WifiPresenter {

    private static final String LOGTAG = "WifiPresenter";


    private IWifiView mView;
    private WifiActor mWifiActor;
    private WifiResponse mWifiResponse;
    private Executor mExecutor;


    WifiPresenter(){

        mWifiActor = new WifiActor();
        mExecutor = new Executor();
    }


    void attachView(IWifiView view){

        this.mView = view;
        if (mWifiResponse == null){
            getWifi();
        }
    }


    void detachView(){

        this.mView = null;
    }


    String getState(){

        return mWifiResponse.storeInString();
    }


    void setState(String state){

        mWifiResponse.restoreFromString(state);
    }


    /* *********************************************************************************************
     * DOMAIN ACTIONS
     * *********************************************************************************************
     */


    void getWifi(){

        mWifiActor.subscribe(getClass().getSimpleName(), wifiCallback, mExecutor);
        mExecutor.executeInSingleThread(mWifiActor);
    }


    void updateWifi(){

    }


    /* *********************************************************************************************
     * VIEW ACTIONS
     * *********************************************************************************************
     */





    /* *********************************************************************************************
     * CALLBACKS
     * *********************************************************************************************
     */


    private BaseActor.BaseCallback<WifiResponse> wifiCallback = new BaseActor.BaseCallback<WifiResponse>() {


        @Override
        public void onSuccess(String actorName, WifiResponse data) {
            Log.d(LOGTAG, "onSuccess: " + actorName);

            mWifiResponse = data;

            mView.showWifiInfo(data.getWifiInformation().getSSID());

            mView.showConnectedDevices(data.getConnectedDevices());
        }


        @Override
        public void onDataChanged(String actorName, WifiResponse data) {
            Log.d(LOGTAG, "onDataChanged: " + actorName);
        }


        @Override
        public void onError(String actorName, String message) {
            Log.d(LOGTAG, "onError: " + actorName);
        }

    };



}
