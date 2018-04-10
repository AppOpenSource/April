package com.point.april.ui.activity.personal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.point.april.R;
import com.point.april.bean.Inform;
import com.point.april.bean.request.InformRequest;
import com.point.april.common.log.LogManager;
import com.point.april.contract.MainPageContract;
import com.point.april.data.PreferencesUtil;
import com.point.april.global.GlobalConstant;
import com.point.april.presenter.MainPagePresenterImpl;
import com.point.april.ui.adapter.CommunityMsgAdapter;

import java.util.List;

/**
 * 通知中心页面
 */
public class NotificationActivity extends AppCompatActivity implements View.OnClickListener,
        MainPageContract.View, CommunityMsgAdapter.OnCallback {

    private static final String TAG = NotificationActivity.class.getSimpleName();
    private TextView mTitle;
    private LinearLayout mGoBack;
    private ListView mNotificationListView;
    private CommunityMsgAdapter mAdapter;
    private List<Inform> mNotifyList;
    private MainPageContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        LogManager.d(TAG, "onCreate");
        // 记录打开通知中心次数
        int count = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_NOTIFICATION_COUNT);
        PreferencesUtil.write(this, GlobalConstant.SHARE_KEY_NOTIFICATION_COUNT, ++count);

        mTitle = (TextView) findViewById(R.id.title_content);
        mTitle.setText("通知中心");

        mGoBack = (LinearLayout) findViewById(R.id.go_back);
        mGoBack.setOnClickListener(this);

        mPresenter = new MainPagePresenterImpl(this, this);
        InformRequest req = new InformRequest();
        req.setTag(GlobalConstant.REQ_TAG_GET_NEW_INFORM);
        mPresenter.getNewInforms(req);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                this.finish();
            break;
        }
    }

    @Override
    public void showCommunityInformsError() {
        LogManager.d(TAG, "showCommunityInformsError");
    }

    @Override
    public void showCommunityInforms(List<Inform> list) {
        LogManager.d(TAG, "showCommunityInforms");
        mNotificationListView = (ListView) findViewById(R.id.notification_list);
        //mNotifyList = MockData.getCoachList();
        mNotifyList = list;
        mAdapter = new CommunityMsgAdapter(NotificationActivity.this, mNotifyList, this);
        mNotificationListView.setAdapter(mAdapter);
        mNotificationListView.setFocusable(true);
        mNotificationListView.requestFocus();
        mNotificationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
              //  Intent intent = new Intent(NotificationActivity.this, CoachReviewActivity.class);
              //  intent.putExtra("coach_id", "10001");
              //  startActivity(intent);
            }
        });
    }

    @Override
    public void setPresenter(MainPageContract.Presenter presenter) {
        LogManager.d(TAG, "setPresenter");
    }

    @Override
    public void click(View v, String isGreat, String informID) {

    }
}
