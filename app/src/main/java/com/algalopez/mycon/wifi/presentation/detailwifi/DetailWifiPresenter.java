package com.algalopez.mycon.wifi.presentation.detailwifi;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.domain.interactor.GetCurrentWifiActor;
import com.algalopez.mycon.wifi.domain.interactor.GetSpecificWifiActor;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;
import com.algalopez.mycon.wifi.domain.response.WifiResponse;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/12/17
 */

public class DetailWifiPresenter {


    private static final String LOGTAG = "DetailWifiPresenter";

    private Executor mExecutor;
    private IWifiDbRepo mWifiDbRepo;

    private IDetailWifiView mView;

    private Long mWifiID;



    private GetSpecificWifiActor mGetSpecificWifiActor;
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

        this.mView = null;
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


    void getDetailWifi(){

        if (mGetSpecificWifiActor == null){
            mGetSpecificWifiActor = new GetSpecificWifiActor(mWifiID, mExecutor, mWifiDbRepo);
        }

        if (mGetSpecificWifiActor.isRunning()){
            mView.showError("asdasd");
            return;
        }

        Log.d(LOGTAG, "Executing");
        // Subscribe and execute
        mGetSpecificWifiActor.subscribe(getClass().getSimpleName(), wifiResponseCallback);
        mExecutor.executeInSingleThread(mGetSpecificWifiActor);

    }


    /* *********************************************************************************************
     * CALLBACKS
     * *********************************************************************************************
     */


    private BaseActor.BaseCallback<WifiResponse> wifiResponseCallback = new BaseActor.BaseCallback<WifiResponse>() {


        @Override
        public void onSuccess(String actorName, WifiResponse data) {

            Log.d(LOGTAG, "onSuccess: " + actorName);
            mWifiResponse = data;
            mView.showWifiInfo(mWifiResponse.getWifiInformation());
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
