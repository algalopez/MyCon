package com.algalopez.mycon.wifi.domain.interactor;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.domain.response.AllWifiResponse;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/7/17
 */

public class GetAllWifiActor extends BaseActor<AllWifiResponse> {


    private static final String LOGTAG = "GetAllWifiActor";

    private final String mActorName;
    private IWifiDbRepo mWifiDbRepo;
    private AllWifiResponse mData;

    private Long mWifiID;

    public GetAllWifiActor(Long wifiID, Executor executor, IWifiDbRepo wifiDbRepo){

        super(executor);

        this.mActorName = getClass().getSimpleName();
        this.mWifiDbRepo = wifiDbRepo;
        this.mData = new AllWifiResponse();
        this.mWifiID = wifiID;
    }


    /**
     *
     */
    @Override
    public void run() {

        setRunning(true);

        Log.d(LOGTAG, "GetAllWifiActor running");

        mData.setAllWifi(mWifiDbRepo.getAllWifi());

        notifySuccess(mActorName, mData);

        setRunning(false);

    }

}
