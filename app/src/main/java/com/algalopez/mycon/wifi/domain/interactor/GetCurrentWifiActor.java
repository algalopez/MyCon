package com.algalopez.mycon.wifi.domain.interactor;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.data.IWifiManagerRepo;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;
import com.algalopez.mycon.wifi.domain.response.WifiResponse;
import com.algalopez.mycon.wifi.domain.utils.WifiUtils;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

public class GetCurrentWifiActor extends BaseActor<WifiResponse> {

    private static final String LOGTAG = "GetCurrentWifiActor";

    private final String mActorName;
    private IWifiDbRepo mWifiDbRepo;
    private IWifiManagerRepo mWifiManagerRepo;
    private WifiResponse mData;

    public GetCurrentWifiActor(Executor executor, IWifiDbRepo wifiDbRepo, IWifiManagerRepo wifiManagerRepo){

        super(executor);

        this.mActorName = getClass().getSimpleName();
        this.mWifiDbRepo = wifiDbRepo;
        this.mWifiManagerRepo = wifiManagerRepo;
        this.mData = new WifiResponse();
    }


    /**
     *
     */
    @Override
    public void run() {

        setRunning(true);
        notifyDataChange(mActorName, mData);

        Log.d(LOGTAG, "GetCurrentWifiActor running");

        // Check if it's connected to a wifi
        if (!mWifiManagerRepo.isWifiConnected()){
            mData.setState(WifiResponse.ERROR_WIFI_NOT_CONNECTED);
            notifyError(mActorName, mData);
            setRunning(false);
            return;
        }

        // Get Wifi information
        WifiEntity wifiEntity = mWifiManagerRepo.getWifi();

        // Get wifi ID by SSID
        Long wifiID = mWifiDbRepo.getWifiIDBySSID(wifiEntity.getSSID());
        wifiEntity.setID(wifiID);

        // Update response
        mData.setWifiInformation(wifiEntity);

        // Log Wifi
        Log.d(LOGTAG, wifiEntity.toString());

        // If wifi doesn't exist in database, then add it
        if (wifiID < 0) {
            wifiID = mWifiDbRepo.storeWifi(wifiEntity);
            wifiEntity.setID(wifiID);
            mData.setState(WifiResponse.ERROR_NEW_WIFI);
        }

        // Get connected devices from database
        ArrayList<DeviceEntity> deviceEntities = mWifiDbRepo.getConnectedDevices(wifiID);
        mData.setConnectedDevices(deviceEntities);

        notifySuccess(mActorName, mData);

        setRunning(false);
    }







}
