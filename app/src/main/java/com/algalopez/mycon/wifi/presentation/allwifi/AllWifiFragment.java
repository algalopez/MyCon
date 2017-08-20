package com.algalopez.mycon.wifi.presentation.allwifi;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.algalopez.mycon.R;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.data.WifiDbRepo;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;


public class AllWifiFragment extends Fragment implements IAllWifiView {


    private View mRootView;
    private AllWifiAdapter mAdapter;
    private AllWifiPresenter mPresenter;
    private Executor mExecutor;
    private IWifiDbRepo mWifiDbRepo;

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
        mExecutor = new Executor();
        mWifiDbRepo = new WifiDbRepo(getContext());
        mAdapter = new AllWifiAdapter(getContext());
        mPresenter = new AllWifiPresenter(mExecutor, mWifiDbRepo);

        ListView listView = (ListView) mRootView.findViewById(R.id.allwifi_list_lv);
        listView.setAdapter(mAdapter);

        setHasOptionsMenu(true);

        return mRootView;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
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

        Toast.makeText(getContext(), percentage + "%", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showError(String message) {

        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showAllWifi(ArrayList<WifiEntity> wifiEntities) {

        mAdapter.setData(wifiEntities);
    }


    /* *********************************************************************************************
     * ACTIVITY CALLBACK
     * *********************************************************************************************
     */


    interface OnFragmentInteractionListener {

        void onWifiSelected(WifiEntity wifiEntity);
    }

}
