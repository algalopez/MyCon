package com.algalopez.mycon.wifi.presentation.allwifi;

import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/19/17
 */

interface IAllWifiView {

    void showProgress(int percentage);

    void showError(String message);

    void showAllWifi(ArrayList<WifiEntity> wifiEntities);
}
