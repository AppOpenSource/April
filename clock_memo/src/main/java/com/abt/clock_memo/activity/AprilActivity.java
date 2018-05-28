package com.abt.clock_memo.activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.abt.clock_memo.R;
import com.j256.ormlite.support.ConnectionSource;
import com.point.april.R;
import com.point.april.common.log.LogManager;
import com.point.april.data.db.mysql.MySQLHelper;
import com.point.april.global.GlobalConstant;
import com.point.april.ui.activity.coach.CoachActivity;
import com.point.april.ui.activity.news.NewsActivity;
import com.point.april.ui.activity.personal.NotificationActivity;
import com.point.april.util.StatusBarCompat;

/**
 * @描述： @App首页
 * @作者： @黄卫旗
 * @创建时间： @28/05/2018
 */
public class AprilActivity extends com.point.april.ui.activity.BaseActivity implements View.OnClickListener {

    private static final String TAG = AprilActivity.class.getSimpleName();
    private TextView mTitle;
    private LinearLayout mGoBack;
    private Button mSignIn;
    private Button mSignRecord;
    private Button mCoachReview;
    private Button mNewsRefined;
    private Button mNotification;
    private Button mAppCount;
    private Button mReWrite;

    private int mCount = 0; //返回键计数器

//    private Handler mCountingHandler = new Handler();
//    private Handler mDisplayHandler = new Handler();
//    private Date lastUpdateTime;//上一次User有动作的TimeStamp
//
//    private long intervalScreenSaver = 1000;//时间间隔
//    private long intervalKeypadeSaver = 1000;
//    private long timePeriod;//计算User有几秒没有动作的
//
//    private float mHoldStillTime = 35;//静止超过N秒将自动进入屏保
//    private boolean isRunScreenSaver;//识当前是否进入了屏保
//    public static boolean isRunningSaver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_april);
        StatusBarCompat.compat(this, 0xFF393A3E);

        onInitView();

        new AddTask().execute();

        // 初始取得User可触碰屏幕的时间
        // lastUpdateTime = new Date(System.currentTimeMillis());

        // setStatusBar();

    }

    @Override
    protected void onInitView() {
        super.onInitView();

        mTitle = (TextView) findViewById(R.id.title_content);
        mTitle.setText(getResources().getText(R.string.app_name));

        mGoBack = (LinearLayout) findViewById(R.id.go_back);
        mGoBack.setOnClickListener(this);
        mGoBack.setVisibility(View.GONE);

        mSignIn = (Button) findViewById(R.id.sign_in);
        mSignIn.setOnClickListener(this);
        mSignRecord = (Button) findViewById(R.id.sign_record);
        mSignRecord.setOnClickListener(this);
        mCoachReview = (Button) findViewById(R.id.coach_review);
        mCoachReview.setOnClickListener(this);
        mNewsRefined = (Button) findViewById(R.id.news_refined);
        mNewsRefined.setOnClickListener(this);
        mNotification = (Button) findViewById(R.id.notification_center);
        mNotification.setOnClickListener(this);
        mAppCount = (Button) findViewById(R.id.app_count);
        mAppCount.setOnClickListener(this);
        mReWrite = (Button) findViewById(R.id.re_write);
        mReWrite.setOnClickListener(this);
    }


    @Override
    protected void onResume() {
        LogManager.d(TAG, "onResume--->");
        //mCountingHandler.postAtTime(mCountingTimeTask, intervalKeypadeSaver);//activity显示的时候启动线程
        super.onResume();
    }

    @Override
    protected void onPause() {
        LogManager.d(TAG, "onPause--->");
        super.onPause();
        //mCountingHandler.removeCallbacks(mCountingTimeTask);//activity不可见的时候取消线程
        //mDisplayHandler.removeCallbacks(mDiaplyTask);
    }

    private class AddTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            Log.d("AprilActivity", "doInBackground : ");
            ConnectionSource connection = MySQLHelper.getConnSrc();
            if (connection != null) {
                /*List<Dish> list = MySQLHelper.getDish();
                if (list != null) {
                    Log.d("AprilActivity", "list : "+list.size());

                }*/
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("AprilActivity", "onPostExecute : ");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                this.finish();
                break;
            case R.id.sign_in:
                Intent iSign = new Intent(this, com.point.april.ui.activity.SignInActivity.class);
                iSign.putExtra(GlobalConstant.SIGN_IN_OR_NOT, "yes");
                startActivity(iSign);
                break;
            case R.id.sign_record:
                Intent signRecord = new Intent(this, com.point.april.ui.activity.SignInActivity.class);
                signRecord.putExtra(GlobalConstant.SIGN_IN_OR_NOT, "no");
                startActivity(signRecord);
                break;
            case R.id.coach_review:
                Intent iReview = new Intent(this, CoachActivity.class);
                startActivity(iReview);
                break;
            case R.id.news_refined:
                Intent news = new Intent(this, NewsActivity.class);
                startActivity(news);
                break;
            case R.id.notification_center:
                Intent notify = new Intent(this, NotificationActivity.class);
                startActivity(notify);
                break;
            case R.id.app_count:
                Intent appCount = new Intent(this, AppCountActivity.class);
                startActivity(appCount);
                break;
            case R.id.re_write:
                Toast.makeText(this, "TODO...", Toast.LENGTH_LONG).show();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
            if (mCount == 1) {
                this.finish();
            } else if (mCount == 0) {
                if (mTitle != null) {
                    mTitle.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mCount = 0;
                        }
                    }, 1000);
                }
                Toast.makeText(this, "再按一次退出" + getResources().getText(R.string.app_name), Toast.LENGTH_SHORT).show();
                mCount++;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // 计时线程
    /*private Runnable mCountingTimeTask = new Runnable() {

        @Override
        public void run() {
            LogManager.d(TAG, "mCountingTimeTask run()");
            Date timeNow = new Date(System.currentTimeMillis());
            // 计算User静止不动作的时间间距
            // 当前的系统时间 - 上次触摸屏幕的时间 = 静止不动的时间
            timePeriod = (long) timeNow.getTime() - (long) lastUpdateTime.getTime();

            // 将静止时间毫秒换算成秒
            float timePeriodSecond = ((float) timePeriod / 1000);

            if(timePeriodSecond > mHoldStillTime){
                if(isRunScreenSaver == false){  //说明没有进入屏保
                    LogManager.d(TAG, "启动线程去显示屏保");
                    mDisplayHandler.postAtTime(mDiaplyTask, intervalScreenSaver);
                    isRunScreenSaver = true;//显示屏保置为true
                }else{
                    LogManager.d(TAG, "屏保正在显示中");
                }
            }else{
                LogManager.d(TAG, "说明静止之间没有超过规定时长");
                isRunScreenSaver = false;
            }
            // 反复调用自己进行检查
            mCountingHandler.postDelayed(mCountingTimeTask, intervalKeypadeSaver);
        }
    };*/

    // 持续屏保显示线程
    /*private Runnable mDiaplyTask = new Runnable() {
        @Override
        public void run() {
            LogManager.d(TAG, "mDiaplyTask run----->");
            if (isRunScreenSaver == true && !isRunningSaver) {  //如果屏保正在显示，就计算不断持续显示
                showScreenSaver();
                mDisplayHandler.postDelayed(mDiaplyTask, intervalScreenSaver);
            } else {
                isRunningSaver = false;
                mDisplayHandler.removeCallbacks(mDiaplyTask);  //如果屏保没有显示则移除线程
            }
        }
    };*/

    // 显示屏保
    /*private void showScreenSaver() {
        isRunningSaver = true;
        LogManager.d(TAG, "showScreenSaver 显示屏保------>");
        Intent intent = new Intent(AprilActivity.this, DisplayInputActivity.class);
        startActivity(intent);
        this.finish();
    }*/

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        LogManager.d(TAG, "dispatchKeyEvent--->");
        //updateUserActionTime();
        return super.dispatchKeyEvent(event);
    }

    //用户有操作的时候不断重置静止时间和上次操作的时间
    /*public void updateUserActionTime() {
        LogManager.d(TAG, "updateUserActionTime--->");
        Date timeNow = new Date(System.currentTimeMillis());
        timePeriod = timeNow.getTime() - lastUpdateTime.getTime();
        lastUpdateTime.setTime(timeNow.getTime());
    }*/


}
