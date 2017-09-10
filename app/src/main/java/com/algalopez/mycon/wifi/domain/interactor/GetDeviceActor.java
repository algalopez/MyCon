package com.algalopez.mycon.wifi.domain.interactor;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.response.DeviceResponse;
import com.algalopez.mycon.wifi.domain.response.WifiResponse;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    9/10/17
 */

public class GetDeviceActor extends BaseActor<DeviceResponse> {

    private final String mActorName;
    private IWifiDbRepo mWifiDbRepo;
    //private DeviceResponse mData;
    private Long mDeviceID;


    public GetDeviceActor(Long deviceID, Executor executor, IWifiDbRepo wifiDbRepo){
        super(executor);

        this.mActorName = getClass().getSimpleName();
        this.mWifiDbRepo = wifiDbRepo;
        this.mData = new DeviceResponse();
        this.mDeviceID = deviceID;
    }


    @Override
    public void run() {
        setRunning(true);

        DeviceEntity deviceEntity = mWifiDbRepo.getDevice(mDeviceID);
        mData.setDeviceInformation(deviceEntity);

        if (deviceEntity == null){

            mData.setState(DeviceResponse.ERROR_INVALID_DEVICE);
            notifyError(mActorName, mData);
            setRunning(false);
            return;
        }

        notifySuccess(mActorName, mData);
    }





}
