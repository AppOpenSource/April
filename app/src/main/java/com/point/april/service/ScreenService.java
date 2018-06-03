package com.point.april.service;

import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

import com.point.april.ui.activity.screensaver.DisplayInputActivity;

/**
 * Created by weiqi.huang on 2016/11/11.
 */
public class ScreenService extends Service {

    private static final String TAG = ScreenService.class.getSimpleName();
    private KeyguardManager mKeyguardManager = null;
    private KeyguardLock mKeyguardLock = null;
    private BroadcastReceiver mMasterResetReciever;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        startScreenService();
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        startScreenService();
    }

    private void startScreenService(){
        mKeyguardManager = (KeyguardManager) getSystemService(Context.KEYGUARD_SERVICE);
        mKeyguardLock = mKeyguardManager.newKeyguardLock("");
        mKeyguardLock.disableKeyguard();

        mMasterResetReciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                try {
                    Intent i = new Intent();
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.setClass(context, DisplayInputActivity.class);
                    context.startActivity(i);
                } catch (Exception e) {
                }
            }
        };
        registerReceiver(mMasterResetReciever, new IntentFilter(Intent.ACTION_SCREEN_OFF));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMasterResetReciever);
        ScreenService.this.stopSelf();
    }

}
