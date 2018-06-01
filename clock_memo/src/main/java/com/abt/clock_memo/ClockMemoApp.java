package com.abt.clock_memo;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * @描述： @ClockMemoApp
 * @作者： @黄卫旗
 * @创建时间： @01/06/2018
 */
public class ClockMemoApp extends Application {

    private static final String APP_ID = "d58b2318c3";

    @Override
    public void onCreate() {
        super.onCreate();
        /**
         * 第三个参数为SDK调试模式开关
         * 建议在测试阶段建议设置成true，发布时设置为false。
         */
        CrashReport.initCrashReport(getApplicationContext(), APP_ID, true);

        CrashReport.testJavaCrash();
    }

}
