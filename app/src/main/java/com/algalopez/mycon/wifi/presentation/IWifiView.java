package com.algalopez.mycon.wifi.presentation;

import com.algalopez.mycon.wifi.domain.model.DeviceEntity;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

public interface IWifiView {



    void showProgress(int percentage);

    void showError(String message);

    void showConnectedDevices(ArrayList<DeviceEntity> connectedDevices);

    void showWifiInfo(String WifiSSID);


}
