package com.algalopez.mycon.wifi.presentation.detaildevice;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.algalopez.mycon.R;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;


public class DetailDeviceFragment extends Fragment implements IDetailDeviceView {


    private View mRootView;


    /* *********************************************************************************************
     * CONSTRUCTION
     * *********************************************************************************************
     */

    private static final String ARG_DEVICEID = "deviceID";
    private Long mDeviceID;

    public DetailDeviceFragment() { }


    public static DetailDeviceFragment newInstance(Long deviceID) {
        DetailDeviceFragment fragment = new DetailDeviceFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_DEVICEID, deviceID);

        fragment.setArguments(args);
        return fragment;
    }


    /* *********************************************************************************************
     * FRAGMENT LIFECYCLE
     * *********************************************************************************************
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mDeviceID = getArguments().getLong(ARG_DEVICEID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_detail_device, container, false);

        setHasOptionsMenu(true);

        return mRootView;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    /* *********************************************************************************************
     * LISTENERS
     * *********************************************************************************************
     */




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
    public void showDeviceInfo(DeviceEntity deviceEntity) {

    }


    /* *********************************************************************************************
     * ACTIVITY CALLBACK
     * *********************************************************************************************
     */


}
