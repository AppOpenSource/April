package com.point.base;

import android.app.Application;
import android.content.Context;

public class BaseApp extends Application {

    private static BaseApp mInstance = new BaseApp();
    private static Context mContext;

    public static BaseApp getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
    }
}
