package com.algalopez.mycon.wifi.presentation.detaildevice;

import com.algalopez.mycon.wifi.domain.model.DeviceEntity;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/19/17
 */

interface IDetailDeviceView {

    void showProgress(int percentage);

    void showError(String message);

    void showDeviceInfo(DeviceEntity deviceEntity);
}


