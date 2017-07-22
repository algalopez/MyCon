package com.algalopez.mycon.Wifi.Presentation;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algalopez.mycon.R;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

public class WifiAdapter extends RecyclerView.Adapter<WifiAdapter.WifiAdapterViewHolder> {


    private static final String LOGTAG = "WifiAdapter";



    public static class WifiAdapterViewHolder extends RecyclerView.ViewHolder{


        private TextView connectedDevice_ip_tv;


        WifiAdapterViewHolder(View itemView){
            super(itemView);

            connectedDevice_ip_tv = (TextView) itemView.findViewById(R.id.wifi_connected_device_item_ip);
        }


        public void bind(String c){

            Log.d(LOGTAG, "binding: " + c);
            connectedDevice_ip_tv.setText(c);
        }

    }


    private ArrayList<String> mData;

    public WifiAdapter(ArrayList<String> data){

        Log.d(LOGTAG, "WifiAdapter(data) - size: " + data.size());

        this.mData = data;
    }


    @Override
    public WifiAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Log.d(LOGTAG, "onCreateViewHolder()");

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.wifi_connected_device_item, parent, false);

        return new WifiAdapterViewHolder(itemView);

    }


    @Override
    public void onBindViewHolder(WifiAdapterViewHolder holder, int position) {

        Log.d(LOGTAG, "onBindViewHolder(X, " + position);

        String item = mData.get(position);

        holder.bind(item);

    }


    @Override
    public int getItemCount() {

        Log.d(LOGTAG, "getItemCount()");

        return mData.size();
    }


}
