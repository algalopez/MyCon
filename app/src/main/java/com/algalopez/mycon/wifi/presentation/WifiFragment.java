package com.algalopez.mycon.wifi.presentation;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algalopez.mycon.R;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;

import java.util.ArrayList;



public class WifiFragment extends Fragment implements IWifiView {


    private static final String LOGTAG = "WifiFragment";
    private static final String SAVEDINSTANCE_KEY = "SAVEDINSTANCE";


    private View mRootView;
    private WifiPresenter mPresenter;


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
        mPresenter = new WifiPresenter();

//        if (savedInstanceState != null) {
//            Log.d(LOGTAG, "onCreateView: " + savedInstanceState.getString(SAVEDINSTANCE_KEY));
//            mPresenter.setState(savedInstanceState.getString(SAVEDINSTANCE_KEY));
//        }

//        WifiAdapter connectedDevicesAdapter = new WifiAdapter(null);
//
//        RecyclerView connectedDevicesList = (RecyclerView) mRootView.findViewById(R.id.wifi_connected_devices_rv);
//        connectedDevicesList.setAdapter(connectedDevicesAdapter);
//        connectedDevicesList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        setHasOptionsMenu(true);

        return mRootView;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_update) {
            if (mPresenter!= null) {
                mPresenter.updateWifi();
            }
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Log.d(LOGTAG, "onSaveInstanceState: " + mPresenter.getState());

        //outState.putString(SAVEDINSTANCE_KEY, mPresenter.getState());
    }


    @Override
    public void onResume() {
        super.onResume();

        mPresenter.attachView(this);
    }


    @Override
    public void onPause() {
        super.onPause();

        mPresenter.detachView();
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
    public void showConnectedDevices(ArrayList<DeviceEntity> connectedDevices) {

        WifiAdapter connectedDevicesAdapter = new WifiAdapter(connectedDevices);

        RecyclerView connectedDevicesList = (RecyclerView) mRootView.findViewById(R.id.wifi_connected_devices_rv);
        connectedDevicesList.setAdapter(connectedDevicesAdapter);
        connectedDevicesList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }


    @Override
    public void showWifiInfo(String ssid) {

        // Log.d(LOGTAG, "showWifiInfo: " + ssid);
        TextView wifiSSID = (TextView) mRootView.findViewById(R.id.fragment_wifi_ssid_tv);
        wifiSSID.setText(ssid);
    }

}
