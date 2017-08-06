package com.algalopez.mycon.common;

import java.util.HashMap;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/29/17
 */

public abstract class BaseActor<R> implements Runnable{


    private HashMap<String, BaseCallback<R>> bSubscriptions;
    private Executor mExecutor;

    private boolean mRunning = false;

    public BaseActor(Executor executor) {
        this.mExecutor = executor;
    }


    /* *********************************************************************************************
     * USE CASE LOGIC
     * *********************************************************************************************
     */


    public abstract void run();


    /* *********************************************************************************************
     * SUBSCRIBERS
     * *********************************************************************************************
     */


    public void subscribe(String className, BaseCallback<R> callback){

        if (bSubscriptions == null){
            bSubscriptions = new HashMap<>();
        }

        bSubscriptions.put(className, callback);
    }


    public void unSubscribe(String className){

        bSubscriptions.remove(className);
    }


    /* *********************************************************************************************
     * SUBSCRIBERS NOTIFICATION
     * *********************************************************************************************
     */


    protected void notifySuccess(final String actorName, final R data){
        mExecutor.executeInMainThread(new Runnable() {
            @Override
            public void run() {
                for (String key: bSubscriptions.keySet()){
                    bSubscriptions.get(key).onSuccess(actorName, data);
                }
            }
        });
    }


    protected void notifyError(final String actorName, final R data){

        mExecutor.executeInMainThread(new Runnable() {
            @Override
            public void run() {
                for (String key : bSubscriptions.keySet()) {
                    bSubscriptions.get(key).onError(actorName, data);
                }
            }
        });
    }


    protected void notifyDataChange(final String actorName, final R data){

        mExecutor.executeInMainThread(new Runnable() {
            @Override
            public void run() {
                for (String key : bSubscriptions.keySet()) {
                    bSubscriptions.get(key).onDataChanged(actorName, data);
                }
            }
        });
    }



    /* *********************************************************************************************
     * STATE
     * *********************************************************************************************
     */

    protected void setRunning(boolean isRunning){
        mRunning = isRunning;
    }


    public boolean isRunning(){
        return mRunning;
    }


    /* *********************************************************************************************
     * CALLBACK INTERFACE
     * *********************************************************************************************
     */


    public interface BaseCallback<R>{

        void onSuccess(String actorName, R data);
        void onError(String actorName, R data);
        void onDataChanged(String actorName, R data);
    }

}
