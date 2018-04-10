package com.point.april.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.point.april.R;
import com.point.april.data.PreferencesUtil;
import com.point.april.global.GlobalConstant;

/**
 * 应用数据统计页
 */
public class AppCountActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTitle;
    private TextView mAppCount;
    private LinearLayout mGoBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_count);

        mTitle = (TextView) findViewById(R.id.title_content);
        mTitle.setText("应用数据统计");

        mGoBack = (LinearLayout) findViewById(R.id.go_back);
        mGoBack.setOnClickListener(this);

        mAppCount = (TextView) findViewById(R.id.app_count);
    }

    @Override
    protected void onStart() {
        super.onStart();

        int appOpenCount = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_APP_OPEN_COUNT);
        int signInCount = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_SIGN_IN_COUNT);
        int browseSignInRecordCount = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_SIGN_IN_RECORD_COUNT);
        int openCoachReviewCount = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_OPEN_COACH_COUNT);
        int openNewsCount = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_OPEN_NEWS_COUNT);
        int notificationCount = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_NOTIFICATION_COUNT);
        int total = appOpenCount + signInCount +
                browseSignInRecordCount + openCoachReviewCount +
                openNewsCount + notificationCount;

        StringBuilder sb = new StringBuilder();
        sb.append("应用打开次数："+appOpenCount+"次\n\n");
        sb.append("打卡次数："+signInCount+"次\n\n");
        sb.append("查看打卡记录次数："+browseSignInRecordCount+"次\n\n");
        sb.append("打开教练点评次数："+openCoachReviewCount+"次\n\n");
        sb.append("打开新闻精选次数："+openNewsCount+"次\n\n");
        sb.append("打开通知中心次数："+notificationCount+"次\n\n");
        sb.append("总操作次数："+total+"次\n\n");

        mAppCount.setText(sb.toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                this.finish();
            break;
        }
    }
}
