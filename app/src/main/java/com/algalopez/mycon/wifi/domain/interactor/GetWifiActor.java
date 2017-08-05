package com.algalopez.mycon.wifi.domain.interactor;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

public class GetWifiActor extends BaseActor<GetWifiResponse> {

    private final String mActorName;
    private GetWifiResponse mData;

    public GetWifiActor(){
        this.mData = new GetWifiResponse();
        this.mActorName = getClass().getSimpleName();
    }

    @Override
    public void run() {

        WifiEntity wifiEntity = mData.getWifiInformation();
        wifiEntity.setSSID("MOVISTAR_AA00");

        ArrayList<DeviceEntity> connectedDevices = mData.getConnectedDevices();
        DeviceEntity device;
        for (int j=0; j < 4; j++) {
            device = new DeviceEntity();
            device.setIP("192.168.1." + j);
            device.setMAC("A0:B1:C2:D3:E4:F5:G6:H" + j);
            device.setBrand("Nokia " + j);
            device.setName("Alias " + j);
            connectedDevices.add(device);
        }

        notifySuccess(mActorName, mData);



    }

}
