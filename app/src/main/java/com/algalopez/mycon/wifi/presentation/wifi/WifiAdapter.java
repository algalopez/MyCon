package com.algalopez.mycon.wifi.presentation.wifi;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.algalopez.mycon.R;
import com.algalopez.mycon.wifi.domain.model.DeviceEntity;

import java.util.ArrayList;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/22/17
 */

class WifiAdapter extends RecyclerView.Adapter<WifiAdapter.WifiAdapterViewHolder> {


    private static final String LOGTAG = "WifiAdapter";

    private ArrayList<DeviceEntity> mData;

    private RecyclerListener mListener;


    /* *********************************************************************************************
     * VIEWHOLDER PATTERN
     * *********************************************************************************************
     */


    static class WifiAdapterViewHolder extends RecyclerView.ViewHolder{

        private TextView connectedDevice_ip_tv;
        private TextView connectedDevice_mac_tv;
        private TextView connectedDevice_name_tv;
        private TextView connectedDevice_brand_tv;


        /*
         * Constructor
         */
        WifiAdapterViewHolder(View itemView){

            super(itemView);

            connectedDevice_ip_tv = (TextView) itemView.findViewById(R.id.device_item_ip);
            connectedDevice_mac_tv = (TextView) itemView.findViewById(R.id.device_item_mac);
            connectedDevice_name_tv = (TextView) itemView.findViewById(R.id.device_item_name);
            connectedDevice_brand_tv = (TextView) itemView.findViewById(R.id.device_item_brand);

            if (connectedDevice_ip_tv == null){
                Log.e(LOGTAG, "WifiAdapterViewHolder: connectedDevice_ip_tv should't be null");
            }
        }


        /*
         * Bind the data to the view
         */
        void bind(final DeviceEntity device){

            connectedDevice_ip_tv.setText(device.getIP());
            connectedDevice_mac_tv.setText(device.getMAC());
            connectedDevice_name_tv.setText(device.getName());
            connectedDevice_brand_tv.setText(device.getBrand());

        }

    }


    /* *********************************************************************************************
     * LSITENERS
     * *********************************************************************************************
     */


    interface RecyclerListener{
        void OnItemClickListener(DeviceEntity deviceEntity);
    }



    /* *********************************************************************************************
     * RECYCLER ADAPTER
     * *********************************************************************************************
     */


    /*
     * Constructor
     */
    WifiAdapter(ArrayList<DeviceEntity> data){

        this.mData = data;
    }


    WifiAdapter(ArrayList<DeviceEntity> data, RecyclerListener listener){

        this.mData = data;
        this.mListener = listener;
    }


    /**
     * Called by RecyclerView to create the viewholder
     *
     */
    @Override
    public WifiAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        //itemView.setOnClickListener();
        return new WifiAdapterViewHolder(itemView);
    }


    /**
     * Called by RecyclerView to display the data at the specified position
     *
     */
    @Override
    public void onBindViewHolder(WifiAdapterViewHolder holder, int position) {

        final DeviceEntity item = mData.get(position);
        holder.bind(item);


        // Set Listeners
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.OnItemClickListener(item);
            }
        });
    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     */
    @Override
    public int getItemCount() {

        if (mData != null) {
            return mData.size();
        } else {
            return 0;
        }
    }




}
