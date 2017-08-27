package com.algalopez.mycon.wifi.presentation.detailwifi;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.algalopez.mycon.R;
import com.algalopez.mycon.about.AboutActivity;
import com.algalopez.mycon.wifi.presentation.allwifi.AllWifiActivity;

public class DetailWifiActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_wifi);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.detailwifi_toolbar);
//        setSupportActionBar(toolbar);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

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



}
