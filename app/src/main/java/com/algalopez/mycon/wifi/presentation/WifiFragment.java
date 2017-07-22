package com.algalopez.mycon.wifi.presentation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algalopez.mycon.R;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class WifiFragment extends Fragment implements IWifiView {


    private static final String LOGTAG = "WifiFragment";

    private View mRootView;




    public WifiFragment() {
    }


    /* *********************************************************************************************
     * FRAGMENT LIFECYCLE
     * *********************************************************************************************
     */


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_wifi, container, false);
        return mRootView;
    }


    @Override
    public void onResume() {
        super.onResume();

        test1();

    }


    @Override
    public void onPause() {
        super.onPause();

    }



    /* *********************************************************************************************
     * VIEW INTERFACE
     * *********************************************************************************************
     */


    @Override
    public void showProgress(int percentage) {

    }


    @Override
    public void showError(String message) {

    }


    @Override
    public void showConnectedDevices(ArrayList<String> connectedDevices) {


        WifiAdapter connectedDevicesAdapter = new WifiAdapter(connectedDevices);

        RecyclerView connectedDevicesList = (RecyclerView) mRootView.findViewById(R.id.wifi_connected_devices_rv);
        connectedDevicesList.setAdapter(connectedDevicesAdapter);
        connectedDevicesList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }


    @Override
    public void showWifiInfo(String ssid) {
        TextView wifiSSID = (TextView) mRootView.findViewById(R.id.fragment_wifi_ssid_tv);
        wifiSSID.setText(ssid);
    }



    /* *********************************************************************************************
     * TEST VIEW
     * *********************************************************************************************
     */


    private void test1(){

        showWifiInfo("NetName");


        ArrayList<String> connectedDevices = new ArrayList<>();
        connectedDevices.add("dev1");
        connectedDevices.add("dev2");
        connectedDevices.add("dev3");
        connectedDevices.add("dev4");
        showConnectedDevices(connectedDevices);



    }



}
