package com.algalopez.mycon.wifi.domain.interactor;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;
import com.algalopez.mycon.wifi.domain.response.WifiResponse;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/27/17
 */

public class GetSpecificWifiActor extends BaseActor<WifiResponse> {

    private final String mActorName;
    private IWifiDbRepo mWifiDbRepo;
    private WifiResponse mData;
    private Long mWifiID;


    public GetSpecificWifiActor(Long wifiID, Executor executor, IWifiDbRepo wifiDbRepo){
        super(executor);

        this.mActorName = getClass().getSimpleName();
        this.mWifiDbRepo = wifiDbRepo;
        this.mData = new WifiResponse();
        this.mWifiID = wifiID;
    }


    @Override
    public void run() {
        setRunning(true);


        WifiEntity wifiEntity = mWifiDbRepo.getWifi(mWifiID);
        mData.setWifiInformation(wifiEntity);

        if (wifiEntity == null) {
            mData.setState(WifiResponse.ERROR_INVALID_WIFI);
            notifyError(mActorName, mData);
            setRunning(false);
            return;
        }

        // Get connected devices from database
        ArrayList<DeviceEntity> deviceEntities = mWifiDbRepo.getConnectedDevices(mWifiID);
        mData.setConnectedDevices(deviceEntities);

        notifySuccess(mActorName, mData);
        setRunning(false);
    }

}
