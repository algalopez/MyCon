package com.algalopez.mycon.wifi.domain.interactor;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;
import com.algalopez.mycon.wifi.domain.response.WifiResponse;


/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/19/17
 */


public class GetWifiActor extends BaseActor<WifiResponse> {

    private static final String LOGTAG = "GetWifiActor";

    private final String mActorName;
    private IWifiDbRepo mWifiDbRepo;
    private WifiResponse mData;
    private Long mWifiID;


    public GetWifiActor(Long wifiID, Executor executor, IWifiDbRepo wifiDbRepo){
        super(executor);

        this.mActorName = getClass().getSimpleName();
        this.mWifiDbRepo = wifiDbRepo;
        this.mData = new WifiResponse();
        this.mWifiID = wifiID;
    }


    @Override
    public void run() {

        setRunning(true);

        Log.d(LOGTAG, "GetWifiActor running");

        WifiEntity wifiEntity = mWifiDbRepo.getWifi(mWifiID);

        if (wifiEntity == null){

            mData.setState(WifiResponse.ERROR_INVALID_WIFI);
            notifyError(mActorName, mData);
            setRunning(false);
            return;
        }

        mData.setWifiInformation(wifiEntity);
        mData.setConnectedDevices(mWifiDbRepo.getConnectedDevices(mWifiID));

        notifySuccess(mActorName, mData);

        setRunning(false);
    }
}
