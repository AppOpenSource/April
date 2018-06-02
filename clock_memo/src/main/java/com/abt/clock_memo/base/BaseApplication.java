package com.abt.clock_memo.base;

import android.app.Application;
import android.content.Context;

import com.abt.clock_memo.util.BuglyUtil;

/**
 * @描述： @BaseApplication
 * @作者： @黄卫旗
 * @创建时间： @02/06/2018
 */
public class BaseApplication extends Application {

    private static Context mContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        BuglyUtil.init();
    }

    public static Context getAppContext() {
        return mContext;
    }

}
