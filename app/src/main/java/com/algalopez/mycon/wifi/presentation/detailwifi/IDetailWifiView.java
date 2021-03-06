package com.algalopez.mycon.wifi.presentation.detailwifi;

import com.algalopez.mycon.wifi.domain.model.WifiEntity;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/19/17
 */

interface IDetailWifiView {

    void showProgress(int percentage);

    void showError(String message);

    void showWifiInfo(WifiEntity wifiEntity);

}
