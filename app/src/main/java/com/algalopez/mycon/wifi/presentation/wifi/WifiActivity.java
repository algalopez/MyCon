package com.algalopez.mycon.wifi.presentation.wifi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.algalopez.mycon.R;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;
import com.algalopez.mycon.wifi.presentation.detailwifi.DetailWifiActivity;

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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
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
        startActivity(intent);
    }

    @Override
    public void onDeviceSelected(DeviceEntity deviceEntity) {

    }
}
