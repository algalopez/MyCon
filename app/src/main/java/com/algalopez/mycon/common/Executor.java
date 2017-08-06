package com.algalopez.mycon.common;


import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/29/17
 */

public class Executor {

    private Handler mHandler;
    private ExecutorService singleThreadExecutorService;


    public Executor(){
        mHandler = new Handler(Looper.getMainLooper());
        singleThreadExecutorService = Executors.newSingleThreadExecutor();
    }


    void executeInMainThread(Runnable runnable){

        mHandler.post(runnable);
    }


    public void executeInSingleThread(Runnable runnable) {

        singleThreadExecutorService.submit(runnable);
    }


}
