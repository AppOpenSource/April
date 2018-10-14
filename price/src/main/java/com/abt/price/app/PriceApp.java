package com.abt.price.app;

import android.util.Log;

import com.abt.common.app.BasicApplication;

/**
 * @描述： @PriceApp
 * @作者： @黄卫旗
 * @创建时间： @10/06/2018
 */
public class PriceApp extends BasicApplication {

    private static final String TAG = "PriceApp";
    private static PriceApp sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
        Log.d(TAG, "onCreate() called");
    }

    public static PriceApp getsInstance() {
        return sInstance;
    }

    @Override
    public void initComplete() {
        Log.d(TAG, "initComplete() called");
    }

}
