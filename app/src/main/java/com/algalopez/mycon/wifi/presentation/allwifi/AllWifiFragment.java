package com.algalopez.mycon.wifi.presentation.allwifi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.algalopez.mycon.R;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;


public class AllWifiFragment extends Fragment implements IAllWifiView {


    private View mRootView;

    private OnFragmentInteractionListener mListener;



    /* *********************************************************************************************
     * CONSTRUCTION
     * *********************************************************************************************
     */


    public AllWifiFragment() { }


    public static AllWifiFragment newInstance() {

        return new AllWifiFragment();
    }


    /* *********************************************************************************************
     * FRAGMENT LIFECYCLE
     * *********************************************************************************************
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mRootView = inflater.inflate(R.layout.fragment_all_wifi, container, false);

        setHasOptionsMenu(true);

        return mRootView;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnFragmentInteractionListener) {

            mListener = (OnFragmentInteractionListener) context;
        } else {

            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
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
    public void showAllWifi(ArrayList<WifiEntity> wifiEntities) {

    }


    /* *********************************************************************************************
     * ACTIVITY CALLBACK
     * *********************************************************************************************
     */


    interface OnFragmentInteractionListener {

        void onWifiSelected(WifiEntity wifiEntity);
    }

}
