package com.algalopez.mycon.wifi.presentation.detailwifi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algalopez.mycon.R;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.data.WifiDbRepo;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;



public class DetailWifiFragment extends Fragment implements IDetailWifiView {


    private static final String LOGTAG = "DetailWifiFragment";

    private View mRootView;

    public static final String ARG_WIFIID = "wifiID";

    private Long mWifiID;

    private DetailWifiPresenter mPresenter;

    private IWifiDbRepo mWifiDbRepo;

    private Executor mExecutor;


    /* *********************************************************************************************
     * CONSTRUCTION
     * *********************************************************************************************
     */


    public DetailWifiFragment() { }


    public static DetailWifiFragment newInstance(Long wifiID) {
        DetailWifiFragment fragment = new DetailWifiFragment();
        Bundle args = new Bundle();
        args.putLong(ARG_WIFIID, wifiID);
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
            mWifiID = getArguments().getLong(ARG_WIFIID);
        } else {

            Bundle bundle = getActivity().getIntent().getExtras();
            if (bundle != null) {

                mWifiID = bundle.getLong(ARG_WIFIID);
            }
        }

        Log.d(LOGTAG, "Wifi ID is " + mWifiID);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_detail_wifi, container, false);

        mExecutor = new Executor();
        mWifiDbRepo = new WifiDbRepo(getContext());
        mPresenter = new DetailWifiPresenter(mWifiID, mExecutor, mWifiDbRepo);


        //setHasOptionsMenu(true);

        return mRootView;
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        return super.onOptionsItemSelected(item);
//    }


    @Override
    public void onResume() {
        super.onResume();

        mPresenter.attachView(this);
        mPresenter.getDetailWifi();
    }


    @Override
    public void onDetach() {
        super.onDetach();

        mPresenter.detachView();
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
        Log.d(LOGTAG, "showProgress " + percentage);
    }


    @Override
    public void showError(String message) {
        Log.d(LOGTAG, "showError " + message);
    }


    @Override
    public void showWifiInfo(WifiEntity wifiEntity) {
        Log.d(LOGTAG, "showWifiInfo");

        TextView wSSID = (TextView) mRootView.findViewById(R.id.detail_wifi_ssid_res_tv);
        wSSID.setText(wifiEntity.getSSID());
    }


    /* *********************************************************************************************
     * ACTIVITY CALLBACK
     * *********************************************************************************************
     */


}
