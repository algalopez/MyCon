package com.algalopez.mycon.common;

import java.util.HashMap;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/29/17
 */

public abstract class BaseActor<R> implements Runnable{


    private HashMap<String, BaseCallback<R>> bSubscriptions;
    private Executor mExecutor;


    public void subscribe(String className, BaseCallback<R> callback, Executor executor){

        this.mExecutor = executor;

        if (bSubscriptions == null){
            bSubscriptions = new HashMap<>();
        }

        bSubscriptions.put(className, callback);
    }

    public void unSubscribe(String className){
        bSubscriptions.remove(className);
    }


    public abstract void run();


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


    protected void notifyError(final String actorName, final String message){

        mExecutor.executeInMainThread(new Runnable() {
            @Override
            public void run() {
                for (String key : bSubscriptions.keySet()) {
                    bSubscriptions.get(key).onError(actorName, message);
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


    public interface BaseCallback<R>{

        void onSuccess(String actorName, R data);
        void onError(String actorName, String message);
        void onDataChanged(String actorName, R data);
    }

}
