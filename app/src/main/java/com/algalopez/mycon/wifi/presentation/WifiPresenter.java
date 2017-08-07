package com.algalopez.mycon.wifi.presentation;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.data.IWifiManagerRepo;
import com.algalopez.mycon.wifi.domain.interactor.GetWifiActor;
import com.algalopez.mycon.wifi.domain.interactor.UpdateWifiActor;
import com.algalopez.mycon.wifi.domain.response.WifiResponse;


/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */


class WifiPresenter {

    private static final String LOGTAG = "WifiPresenter";


    private IWifiView mView;
    private Executor mExecutor;

    private IWifiDbRepo mWifiDbRepo;
    private IWifiManagerRepo mWifiManagerRepo;

    private GetWifiActor mGetWifiActor;
    private UpdateWifiActor mUpdateWifiActor;

    private WifiResponse mWifiResponse;


    WifiPresenter(Executor executor, IWifiDbRepo wifiDbRepo, IWifiManagerRepo wifiManagerRepo){

        mExecutor = executor;
        this.mWifiDbRepo = wifiDbRepo;
        this.mWifiManagerRepo = wifiManagerRepo;
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


    private void getWifi(){

        // Create interactor if it doesn't exist
        if (mGetWifiActor == null) {
            mGetWifiActor = new GetWifiActor(mExecutor, mWifiDbRepo, mWifiManagerRepo);
        }

        // If interactor is already running, then show error
        if (mGetWifiActor.isRunning()){
            Log.d(LOGTAG, "GetWifiActor is already running");
            mView.showError("Already running");
            return;
        }

        // Subscribe and execute interactor
        mGetWifiActor.subscribe(getClass().getSimpleName(), wifiResponseCallback);
        mExecutor.executeInSingleThread(mGetWifiActor);
    }


    void updateWifi(){

        // Create interactor if it doesn't exist
        if (mUpdateWifiActor == null) {
            mUpdateWifiActor = new UpdateWifiActor(mExecutor, mWifiDbRepo, mWifiManagerRepo);
        }

        // If interactor is already running, then show error
        if (mUpdateWifiActor.isRunning()){
            Log.d(LOGTAG, "UpdateWifiActor is already running");
            mView.showError("Already running");
            return;
        }

        // Subscribe and execute interactor
        mUpdateWifiActor.subscribe(getClass().getSimpleName(), wifiResponseCallback);
        mExecutor.executeInSingleThread(mUpdateWifiActor);
    }


    /* *********************************************************************************************
     * VIEW ACTIONS
     * *********************************************************************************************
     */





    /* *********************************************************************************************
     * CALLBACKS
     * *********************************************************************************************
     */


    private BaseActor.BaseCallback<WifiResponse> wifiResponseCallback = new BaseActor.BaseCallback<WifiResponse>() {

        @Override
        public void onSuccess(String actorName, WifiResponse data) {
            Log.d(LOGTAG, "onSuccess: " + actorName);

            mWifiResponse = data;

            mView.showWifiInfo(data.getWifiInformation().getSSID());

            mView.showConnectedDevices(data.getConnectedDevices());
        }


        @Override
        public void onDataChanged(String actorName, WifiResponse data) {
            Log.d(LOGTAG, "onDataChanged: " + actorName + " [progress: " + data.getProgress() + "]");
        }


        @Override
        public void onError(String actorName, WifiResponse data) {
            Log.d(LOGTAG, "onError: " + actorName);
        }

    };


}
