package com.abt.clock_memo;

import com.abt.clock_memo.util.BuglyUtil;
import com.abt.clock_memo.util.WeChatUtil;
import com.abt.common.app.BasicApplication;
import com.abt.common.util.LocationUtil;
import com.tencent.bugly.Bugly;

/**
 * @描述： @ClockMemoApp
 * @作者： @黄卫旗
 * @创建时间： @01/06/2018
 */
public class ClockMemoApp extends BasicApplication {

    private static final String APP_ID = "d58b2318c3";

    @Override
    public void initComplete() {
        LocationUtil.getInstance().init();
        BuglyUtil.init();
        BuglyUtil.checkUpgrade();
        WeChatUtil.reqToWx();
    }

    /**
     * 参看BaseApplication中的BuglyUtil.init()
     */
    private void initBugly() {
        /**
         * 第三个参数为SDK调试模式开关
         * 建议在测试阶段建议设置成true，发布时设置为false。
         */
        //CrashReport.initCrashReport(getApplicationContext(), APP_ID, true);
        Bugly.init(getApplicationContext(), APP_ID, true);
        /**
         * 模拟提交崩溃
         */
        //CrashReport.testJavaCrash();
    }

}
