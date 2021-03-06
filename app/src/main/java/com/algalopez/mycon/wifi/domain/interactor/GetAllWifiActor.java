package com.algalopez.mycon.wifi.domain.interactor;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;
import com.algalopez.mycon.wifi.domain.response.AllWifiResponse;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/7/17
 */

public class GetAllWifiActor extends BaseActor<AllWifiResponse> {


    private static final String LOGTAG = "GetAllWifiActor";

    private final String mActorName;
    private IWifiDbRepo mWifiDbRepo;
    private AllWifiResponse mData;

    public GetAllWifiActor(Executor executor, IWifiDbRepo wifiDbRepo){

        super(executor);

        this.mActorName = getClass().getSimpleName();
        this.mWifiDbRepo = wifiDbRepo;
        this.mData = new AllWifiResponse();
    }


    /**
     *
     */
    @Override
    public void run() {

        setRunning(true);

        Log.d(LOGTAG, "GetAllWifiActor running");

        ArrayList<WifiEntity> wifiEntities = mWifiDbRepo.getAllWifi();

        mData.setAllWifi(wifiEntities);

        notifySuccess(mActorName, mData);

        setRunning(false);

    }

}
