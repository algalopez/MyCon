package com.algalopez.mycon.wifi.presentation;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.domain.interactor.GetWifiActor;
import com.algalopez.mycon.wifi.domain.interactor.GetWifiResponse;


/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */


class WifiPresenter {

    private static final String LOGTAG = "WifiPresenter";


    private IWifiView mView;
    private GetWifiActor mGetWifiActor;
    private GetWifiResponse mGetWifiResponse;
    private Executor mExecutor;


    WifiPresenter(){

        mGetWifiActor = new GetWifiActor();
        mExecutor = new Executor();
    }


    void attachView(IWifiView view){

        this.mView = view;
        if (mGetWifiResponse == null){
            getWifi();
        }
    }


    void detachView(){

        this.mView = null;
    }


    String getState(){

        return mGetWifiResponse.storeInString();
    }


    void setState(String state){

        mGetWifiResponse.restoreFromString(state);
    }


    /* *********************************************************************************************
     * DOMAIN ACTIONS
     * *********************************************************************************************
     */


    void getWifi(){

        mGetWifiActor.subscribe(getClass().getSimpleName(), wifiCallback, mExecutor);
        mExecutor.executeInSingleThread(mGetWifiActor);
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


    private BaseActor.BaseCallback<GetWifiResponse> wifiCallback = new BaseActor.BaseCallback<GetWifiResponse>() {


        @Override
        public void onSuccess(String actorName, GetWifiResponse data) {
            Log.d(LOGTAG, "onSuccess: " + actorName);

            mGetWifiResponse = data;

            mView.showWifiInfo(data.getWifiInformation().getSSID());

            mView.showConnectedDevices(data.getConnectedDevices());
        }


        @Override
        public void onDataChanged(String actorName, GetWifiResponse data) {
            Log.d(LOGTAG, "onDataChanged: " + actorName);
        }


        @Override
        public void onError(String actorName, String message) {
            Log.d(LOGTAG, "onError: " + actorName);
        }

    };



}
