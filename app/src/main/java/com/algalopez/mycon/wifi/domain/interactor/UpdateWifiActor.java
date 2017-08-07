package com.algalopez.mycon.wifi.domain.interactor;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.data.IWifiManagerRepo;
import com.algalopez.mycon.wifi.data.manager.WifiManager;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;
import com.algalopez.mycon.wifi.domain.response.WifiResponse;
import com.algalopez.mycon.wifi.domain.utils.WifiUtils;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Date;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/5/17
 */

public class UpdateWifiActor extends BaseActor<WifiResponse> {

    private static final String LOGTAG = "UpdateWifiActor";

    private final String mActorName;
    private IWifiDbRepo mWifiDbRepo;
    private IWifiManagerRepo mWifiManagerRepo;
    private WifiResponse mData;


    public UpdateWifiActor(Executor executor, IWifiDbRepo wifiDbRepo, IWifiManagerRepo wifiManagerRepo){

        super(executor);

        this.mActorName = getClass().getSimpleName();
        this.mWifiDbRepo = wifiDbRepo;
        this.mWifiManagerRepo = wifiManagerRepo;
        this.mData = new WifiResponse();
    }


    @Override
    public void run() {

        setRunning(true);

        // Check if it's connected to a wifi
        if (!mWifiManagerRepo.isWifiConnected()){
            mData.setState(WifiResponse.ERROR_WIFI_NOT_CONNECTED);
            notifyError(mActorName, mData);
            setRunning(false);
            return;
        }

        // Get Wifi information
        WifiEntity wifiEntity = mWifiManagerRepo.getWifi();
        mData.setWifiInformation(wifiEntity);

        // Check if wifi is giving IPv4 addresses
        if (!WifiUtils.isValidIP4(wifiEntity)){
            mData.setState(WifiResponse.ERROR_INVALID_WIFI);
            notifyError(mActorName, mData);
            setRunning(false);
            return;
        }

        // Insert or upload wifi in database
        Long wifiID = mWifiDbRepo.getWifiIDBySSID(wifiEntity.getSSID());
        if (wifiID < 0) {
            wifiID = mWifiDbRepo.storeWifi(wifiEntity);
        } else {
            wifiEntity.setID(wifiID);
            mWifiDbRepo.updateWifi(wifiEntity);
        }

        // Look for connected devices
        String prefixIPStr = "192.168.1.";
        DeviceEntity deviceEntity;
        for (int j = 1; j < 256; j++) {
            mData.setProgress(j*100/255);
            notifyDataChange(mActorName, mData);
            deviceEntity = mWifiManagerRepo.getDevice(prefixIPStr + j);
            if (deviceEntity != null){
                mData.addConnectedDevice(deviceEntity);
            }
        }

        // Store devices but not update its values
        ArrayList<DeviceEntity> deviceEntities = mData.getConnectedDevices();
        for (DeviceEntity dev: deviceEntities){

            Long deviceID = mWifiDbRepo.getDeviceIDByMAC(dev.getMAC());
            if (deviceID < 0){
                deviceID = mWifiDbRepo.storeDevice(dev);
            }

            dev.setID(deviceID);
        }

        // Store connected devices
        mWifiDbRepo.storeConnectedDevices(wifiID, deviceEntities);

        notifySuccess(mActorName, mData);

        setRunning(false);
    }
}
