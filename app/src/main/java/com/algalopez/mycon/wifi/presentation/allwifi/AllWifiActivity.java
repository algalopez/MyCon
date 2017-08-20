package com.algalopez.mycon.wifi.presentation.allwifi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.algalopez.mycon.R;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

public class AllWifiActivity extends AppCompatActivity implements AllWifiFragment.OnFragmentInteractionListener{


    private static final String LOGTAG = "AllWifiActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_wifi);
    }


    /* *********************************************************************************************
     * FRAGMENT INTERACTION
     * *********************************************************************************************
     */


    @Override
    public void onWifiSelected(WifiEntity wifiEntity) {

        Log.d(LOGTAG, "onWifiSelected ");
    }
}
