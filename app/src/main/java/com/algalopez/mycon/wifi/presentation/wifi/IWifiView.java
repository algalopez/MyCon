package com.algalopez.mycon.wifi.presentation.wifi;

import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

interface IWifiView {

    void showProgress(int percentage);

    void showError(String message);

    void showConnectedDevices(ArrayList<DeviceEntity> connectedDevices);

    void showWifiInfo(WifiEntity wifiEntity);


}
