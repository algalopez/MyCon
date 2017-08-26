package com.algalopez.mycon.wifi.presentation.allwifi;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.domain.interactor.GetAllWifiActor;
import com.algalopez.mycon.wifi.domain.response.AllWifiResponse;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/12/17
 */

class AllWifiPresenter {

    private static final String LOGTAG = "AllWifiPresenter";

    private IAllWifiView mView;
    private Executor mExecutor;
    private IWifiDbRepo mWifiDbRepo;

    private GetAllWifiActor mGetAllWifiActor;

    private AllWifiResponse mAllWifiResponse;



    AllWifiPresenter(Executor executor, IWifiDbRepo wifiDbRepo){
        this.mExecutor = executor;
        this.mWifiDbRepo = wifiDbRepo;
    }


    void attachView(IAllWifiView view){

        this.mView = view;
        if (mAllWifiResponse == null){
            getAllWifi();
        }
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


    private void getAllWifi(){

        // Create interactor if it doesn't exist
        if (mGetAllWifiActor == null){

            mGetAllWifiActor = new GetAllWifiActor(mExecutor, mWifiDbRepo);
        }

        // If interactor is already running, then show error
        if (mGetAllWifiActor.isRunning()){
            Log.d(LOGTAG, "GetAllWifiActor is already running");
            mView.showError("Already running");
            return;
        }

        // Subscribe and execute interactor
        mGetAllWifiActor.subscribe(getClass().getSimpleName(), allWifiResponseCallback);
        mExecutor.executeInSingleThread(mGetAllWifiActor);
    }


    /* *********************************************************************************************
     * VIEW ACTIONS
     * *********************************************************************************************
     */



    /* *********************************************************************************************
     * CALLBACKS
     * *********************************************************************************************
     */

    private BaseActor.BaseCallback<AllWifiResponse> allWifiResponseCallback = new BaseActor.BaseCallback<AllWifiResponse>() {

        @Override
        public void onSuccess(String actorName, AllWifiResponse data) {
            Log.d(LOGTAG, "onSuccess: " + actorName);

            mAllWifiResponse = data;

            if (mView != null){

                mView.showAllWifi(data.getAllWifi());
            }
        }


        @Override
        public void onError(String actorName, AllWifiResponse data) {
            Log.d(LOGTAG, "onDataChanged: " + actorName + " [progress: " + data.getProgress() + "]");

        }


        @Override
        public void onDataChanged(String actorName, AllWifiResponse data) {
            Log.d(LOGTAG, "onError: " + actorName);
        }
    };

}
