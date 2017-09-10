package com.algalopez.mycon.wifi.presentation.detaildevice;

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
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;


public class DetailDeviceFragment extends Fragment implements IDetailDeviceView {


    private static final String LOGTAG = "DetailDeviceFragment";

    private View mRootView;

    public static final String ARG_DEVICEID = "deviceID";

    private Long mDeviceID;

    private DetailDevicePresenter mPresenter;

    private IWifiDbRepo mWifiDbRepo;

    private Executor mExecutor;


    /* *********************************************************************************************
     * CONSTRUCTION
     * *********************************************************************************************
     */


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
        } else {

            Bundle bundle = getActivity().getIntent().getExtras();
            if (bundle != null) {

                mDeviceID = bundle.getLong(ARG_DEVICEID);
            }
        }

        Log.d(LOGTAG, "Device ID is " + mDeviceID);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_detail_device, container, false);

        mExecutor = new Executor();
        mWifiDbRepo = new WifiDbRepo(getContext());
        mPresenter = new DetailDevicePresenter(mDeviceID, mExecutor, mWifiDbRepo);

        return mRootView;
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

        Log.d(LOGTAG, "Showing info");

        // IP
        TextView dev_ip = (TextView) mRootView.findViewById(R.id.detail_device_ip_res_tv);
        dev_ip.setText(deviceEntity.getIP());

        // NAME
        TextView dev_name = (TextView) mRootView.findViewById(R.id.detail_device_name_res_tv);
        dev_name.setText(deviceEntity.getName());

        // BRAND
        TextView dev_brand = (TextView) mRootView.findViewById(R.id.detail_device_brand_res_tv);
        dev_brand.setText(deviceEntity.getBrand());

        // MAC
        TextView dev_mac = (TextView) mRootView.findViewById(R.id.detail_device_mac_res_tv);
        dev_mac.setText(deviceEntity.getMAC());
    }


    /* *********************************************************************************************
     * ACTIVITY CALLBACK
     * *********************************************************************************************
     */


}
