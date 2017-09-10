package com.algalopez.mycon.wifi.presentation.detaildevice;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.domain.interactor.GetDeviceActor;
import com.algalopez.mycon.wifi.domain.response.DeviceResponse;


/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/12/17
 */


class DetailDevicePresenter {

    private static final String LOGTAG = "DetailDevicePresenter";

    private IDetailDeviceView mView;
    private Executor mExecutor;

    private IWifiDbRepo mWifiDbRepo;

    private Long mDeviceID;

    private GetDeviceActor mGetDeviceActor;

    private DeviceResponse mDeviceResponse;


    DetailDevicePresenter(Long deviceID, Executor executor, IWifiDbRepo wifiDbRepo){

        this.mExecutor = executor;
        this.mWifiDbRepo = wifiDbRepo;
        this.mDeviceID = deviceID;
    }


    void attachView(IDetailDeviceView view){

        this.mView = view;
        if (mDeviceResponse == null) {
            getDevice();
        }
    }


    void detachView(){

        this.mView = null;
    }


    String getState(){

        return mDeviceResponse.storeInString();
    }


    void setState(String state){

        mDeviceResponse.restoreFromString(state);
    }


    /* *********************************************************************************************
     * DOMAIN ACTIONS
     * *********************************************************************************************
     */


    private void getDevice(){

        if (mGetDeviceActor == null) {
            mGetDeviceActor = new GetDeviceActor(mDeviceID, mExecutor, mWifiDbRepo);
        }

        if (mGetDeviceActor.isRunning()){
            Log.d(LOGTAG, "GetDeviceActor is already running");
            mView.showError("Already running");
            return;
        }


        // Subscribe and execute interactor
        mGetDeviceActor.subscribe(getClass().getSimpleName(), deviceResponseCallback);
        mExecutor.executeInSingleThread(mGetDeviceActor);
    }


    /* *********************************************************************************************
     * VIEW ACTIONS
     * *********************************************************************************************
     */




    /* *********************************************************************************************
     * CALLBACKS
     * *********************************************************************************************
     */


    private BaseActor.BaseCallback<DeviceResponse> deviceResponseCallback = new BaseActor.BaseCallback<DeviceResponse>() {


        @Override
        public void onSuccess(String actorName, DeviceResponse data) {
            Log.d(LOGTAG, "onSuccess: " + actorName);

            mDeviceResponse = data;
            mView.showDeviceInfo(data.getDeviceInformation());
        }


        @Override
        public void onError(String actorName, DeviceResponse data) {
            Log.d(LOGTAG, "onError: " + actorName);

        }


        @Override
        public void onDataChanged(String actorName, DeviceResponse data) {
            Log.d(LOGTAG, "onDataChanged: " + actorName + " [progress: " + data.getProgress() + "]");

        }
    };


}
