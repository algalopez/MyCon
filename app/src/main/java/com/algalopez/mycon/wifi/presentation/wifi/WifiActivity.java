package com.algalopez.mycon.wifi.presentation.wifi;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.algalopez.mycon.R;
import com.algalopez.mycon.about.AboutActivity;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;
import com.algalopez.mycon.wifi.presentation.allwifi.AllWifiActivity;
import com.algalopez.mycon.wifi.presentation.detailwifi.DetailWifiActivity;
import com.algalopez.mycon.wifi.presentation.detailwifi.DetailWifiFragment;

public class WifiActivity extends AppCompatActivity implements WifiFragment.OnFragmentInteractionListener {

    private static final String LOGTAG = "WifiActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wifi);
        Toolbar toolbar = (Toolbar) findViewById(R.id.wifi_toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wifi, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_about) {

            Intent intentAbout = new Intent(this, AboutActivity.class);
            startActivity(intentAbout);
            return true;
        } else if (id == R.id.action_record) {

            Intent intentRecord = new Intent(this, AllWifiActivity.class);
            startActivity(intentRecord);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    /* *********************************************************************************************
     * FRAGMENT INTERACTION
     * *********************************************************************************************
     */


    @Override
    public void onWifiSelected(WifiEntity wifiEntity) {

        Log.d(LOGTAG, "onWifiSelected ");

        Intent intent = new Intent(this, DetailWifiActivity.class);
        intent.putExtra(DetailWifiFragment.ARG_WIFIID, wifiEntity.getID());
        startActivity(intent);
    }


    @Override
    public void onDeviceSelected(DeviceEntity deviceEntity) {

        Log.d(LOGTAG, "onDeviceSelected ");
    }
}
