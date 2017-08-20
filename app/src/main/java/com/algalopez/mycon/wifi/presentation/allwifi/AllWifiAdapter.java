package com.algalopez.mycon.wifi.presentation.allwifi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.algalopez.mycon.R;
import com.algalopez.mycon.wifi.domain.model.WifiEntity;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/19/17
 */

public class AllWifiAdapter extends BaseAdapter {


    private LayoutInflater mInflater;

    private ArrayList<WifiEntity> mData = new ArrayList<>();


    /* *********************************************************************************************
     * VIEWHOLDER PATTERN
     * *********************************************************************************************
     */


    private static class DataViewHolder {

        final TextView ssidView;
        final TextView lastUpdateView;

        DataViewHolder(View view) {
            ssidView = (TextView) view.findViewById(R.id.item_wifi_ssid_tv);
            lastUpdateView = (TextView) view.findViewById(R.id.item_wifi_lastupdate_tv);
        }
    }


    /* *********************************************************************************************
     *
     * *********************************************************************************************
     */


    AllWifiAdapter(Context context){

        this.mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {

        return mData.size();
    }


    @Override
    public WifiEntity getItem(int i) {

        return mData.get(i);
    }


    @Override
    public long getItemId(int i) {

        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        DataViewHolder dataViewHolder;

        WifiEntity wifiEntity = getItem(i);

        if (view == null) {

            view = mInflater.inflate(R.layout.item_wifi, viewGroup, false);
            dataViewHolder = new DataViewHolder(view);
            view.setTag(dataViewHolder);
        } else {

            dataViewHolder = (DataViewHolder) view.getTag();
        }

        dataViewHolder.ssidView.setText(wifiEntity.getSSID());
        dataViewHolder.lastUpdateView.setText(wifiEntity.getLastUpdated().toString());

        return view;
    }


    /* *********************************************************************************************
     *
     * *********************************************************************************************
     */



    void setData(ArrayList<WifiEntity> wifiEntities){

        mData = wifiEntities;
        notifyDataSetChanged();
    }


    void reset() {

        mData = new ArrayList<>();
        notifyDataSetChanged();
    }



}
