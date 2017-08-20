package com.algalopez.mycon.wifi.presentation.detailwifi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.algalopez.mycon.R;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;



public class DetailWifiFragment extends Fragment implements IDetailWifiView {


    private View mRootView;


    /* *********************************************************************************************
     * CONSTRUCTION
     * *********************************************************************************************
     */


    private static final String ARG_WIFIID = "wifiID";
    private String mWifiID;

    public DetailWifiFragment() { }


    public static DetailWifiFragment newInstance(String wifiID) {
        DetailWifiFragment fragment = new DetailWifiFragment();
        Bundle args = new Bundle();
        args.putString(ARG_WIFIID, wifiID);
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
            mWifiID = getArguments().getString(ARG_WIFIID);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mRootView = inflater.inflate(R.layout.fragment_detail_wifi, container, false);

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
    public void showWifiInfo(WifiEntity wifiEntity) {

    }


    /* *********************************************************************************************
     * ACTIVITY CALLBACK
     * *********************************************************************************************
     */


}
