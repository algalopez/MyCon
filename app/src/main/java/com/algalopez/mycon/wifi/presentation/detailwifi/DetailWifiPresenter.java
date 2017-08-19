package com.algalopez.mycon.wifi.presentation.detailwifi;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.domain.interactor.GetCurrentWifiActor;
import com.algalopez.mycon.wifi.domain.response.WifiResponse;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/12/17
 */

public class DetailWifiPresenter {


    private static final String LOGTAG = "DetailWifiPresenter";

    private Executor mExecutor;
    private IWifiDbRepo mWifiDbRepo;

    IDetailWifiView mView;

    Long mWifiID;

    private GetCurrentWifiActor mGetWifiActor;
    private WifiResponse mWifiResponse;


    DetailWifiPresenter(Long wifiID, Executor executor, IWifiDbRepo wifiDbRepo){

        this.mExecutor = executor;
        this.mWifiDbRepo = wifiDbRepo;
        this.mWifiID = wifiID;
    }


    void attachView(IDetailWifiView view){
        this.mView = view;
    }


    void detachView(){

    }


    String getState(){
        return null;
    }


    void setState(String state){

    }


    /* *********************************************************************************************
     * DOMAIN ACTIONS
     * *********************************************************************************************
     */


    private void getDetailWifi(){

    }


    /* *********************************************************************************************
     * CALLBACKS
     * *********************************************************************************************
     */


    private BaseActor.BaseCallback<WifiResponse> wifiResponseCallback = new BaseActor.BaseCallback<WifiResponse>() {


        @Override
        public void onSuccess(String actorName, WifiResponse data) {

            Log.d(LOGTAG, "onSuccess: " + actorName);
        }

        @Override
        public void onError(String actorName, WifiResponse data) {

            Log.d(LOGTAG, "onDataChanged: " + actorName + " [progress: " + data.getProgress() + "]");
        }

        @Override
        public void onDataChanged(String actorName, WifiResponse data) {
            Log.d(LOGTAG, "onError: " + actorName);
        }
    };




}
