package com.algalopez.mycon.wifi.domain.interactor;

import android.util.Log;

import com.algalopez.mycon.common.BaseActor;
import com.algalopez.mycon.common.Executor;
import com.algalopez.mycon.wifi.data.IWifiDbRepo;
import com.algalopez.mycon.wifi.domain.response.RemoveResponse;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    8/7/17
 */

public class RemoveAllActor extends BaseActor<RemoveResponse> {

    private static final String LOGTAG = "RemoveAllActor";

    private final String mActorName;
    private IWifiDbRepo mWifiDbRepo;
    private RemoveResponse mData;


    public RemoveAllActor(Executor executor, IWifiDbRepo wifiDbRepo){

        super(executor);

        this.mActorName = getClass().getSimpleName();
        this.mWifiDbRepo = wifiDbRepo;
        this.mData = new RemoveResponse();
    }


    @Override
    public void run() {

        setRunning(true);

        Log.d(LOGTAG, "RemoveAllActor running");

        int removedRows = mWifiDbRepo.removeAll();
        mData.setRemovedRows(removedRows);

        notifySuccess(mActorName, mData);

        setRunning(false);
    }

}
