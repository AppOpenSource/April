package com.point.april;

import android.app.Application;
import android.content.Context;

import com.point.april.global.ConfigManager;
import com.point.april.data.PreferencesUtil;
import com.point.april.global.GlobalConstant;
import com.point.april.network.NetworkManager;
import com.point.april.widget.imageloader.ImageLoaderManager;

public class April extends Application {

    public static Context mContect;
    private static April mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        mContect=getApplicationContext();
    }

    private void init() {
        // 记录APP打开次数
        int count = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_APP_OPEN_COUNT);
        PreferencesUtil.write(this, GlobalConstant.SHARE_KEY_APP_OPEN_COUNT, ++count);
        NetworkManager.getInstace().init(this);
        ConfigManager.getInstance().init(getApplicationContext());
        ImageLoaderManager.getInstance().init(getApplicationContext());
    }

    public Context getContext() {
        return getApplicationContext();
    }

    public static April getInstance() {
        if (mInstance == null) {
            try {
                mInstance = April.class.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return mInstance;
    }
}
