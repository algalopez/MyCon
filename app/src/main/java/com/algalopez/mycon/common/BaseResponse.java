package com.algalopez.mycon.common;

/**
 * AUTHOR:  Alvaro Garcia Lopez (algalopez)
 * DATE:    7/23/17
 */

public abstract class BaseResponse {


    public static final int OK = 0;

    private int state;

    private int progress;

    public int getState() { return state; }

    public void setState(int state) { this.state = state; }

    public int getProgress() { return progress; }

    public void setProgress(int progress) { this.progress = progress; }

    public abstract String storeInString();

    public abstract void restoreFromString(String s);

}
