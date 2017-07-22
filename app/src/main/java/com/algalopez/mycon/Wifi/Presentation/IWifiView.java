package com.algalopez.mycon.Wifi.Presentation;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

public interface IWifiView {



    void showProgress(int percentage);

    void showError(String message);

    void showConnectedDevices(ArrayList<String> connectedDevices);

    void showWifiInfo(String WifiSSID);


}
