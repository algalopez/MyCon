package com.algalopez.mycon.wifi.presentation.wifi;

import android.content.Context;
import android.content.Intent;
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
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.data.IWifiManagerRepo;
import com.algalopez.mycon.wifi.data.WifiDbRepo;
import com.algalopez.mycon.wifi.data.WifiManagerRepo;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;
import com.algalopez.mycon.wifi.presentation.detailwifi.DetailWifiActivity;

import java.util.ArrayList;



public class WifiFragment extends Fragment implements IWifiView {


    private static final String LOGTAG = "WifiFragment";

    private View mRootView;

    // Presenter
    private WifiPresenter mPresenter;

    // Stored data
    private IWifiDbRepo mWifiDbRepo;

    // new data
    private IWifiManagerRepo mWifiManagerRepo;

    // Executor
    Executor mExecutor;

    OnFragmentInteractionListener mFragmentListener;



    /* *********************************************************************************************
     * CONSTRUCTION
     * *********************************************************************************************
     */


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

        mExecutor = new Executor();
        mWifiDbRepo = new WifiDbRepo(getContext());
        mWifiManagerRepo = new WifiManagerRepo(getContext());
        mPresenter = new WifiPresenter(mExecutor, mWifiDbRepo, mWifiManagerRepo);

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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mFragmentListener = (OnFragmentInteractionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        mFragmentListener = null;
    }


    /* *********************************************************************************************
     * LISTENERS
     * *********************************************************************************************
     */


    View.OnClickListener onWifiClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            Log.d(LOGTAG, "Wifi clicked");
            mFragmentListener.onWifiSelected(null);
        }
    };


    WifiAdapter.RecyclerListener onItemClickListener = new WifiAdapter.RecyclerListener() {

        @Override
        public void OnItemClickListener(DeviceEntity deviceEntity) {
            Log.d(LOGTAG, "Item with id " + deviceEntity.getID() + " clicked");

        }
    };


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

        WifiAdapter connectedDevicesAdapter = new WifiAdapter(connectedDevices, onItemClickListener);

        RecyclerView connectedDevicesList = (RecyclerView) mRootView.findViewById(R.id.wifi_connected_devices_rv);
        connectedDevicesList.setAdapter(connectedDevicesAdapter);
        connectedDevicesList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
    }


    @Override
    public void showWifiInfo(WifiEntity wifiEntity) {

        TextView wifiSSID = (TextView) mRootView.findViewById(R.id.fragment_wifi_ssid_tv);
        wifiSSID.setText(wifiEntity.getSSID());

        wifiSSID.setOnClickListener(onWifiClickListener);
    }


    /* *********************************************************************************************
     * ACTIVITY CALLBACK
     * *********************************************************************************************
     */


    interface OnFragmentInteractionListener {


        void onWifiSelected(WifiEntity wifiEntity);

        void onDeviceSelected(DeviceEntity deviceEntity);


    }
}

