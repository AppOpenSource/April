package com.point.april.ui.activity.coach;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.point.april.R;
import com.point.april.bean.Coach;
import com.point.april.data.PreferencesUtil;
import com.point.april.global.GlobalConstant;
import com.point.april.test.MockData;
import com.point.april.ui.adapter.CoachReviewListAdapter;

import java.util.List;

/**
 * 教练点评列表页面
 */
public class CoachActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTitle;
    private EditText mSearchBox;
    private LinearLayout mGoBack;
    private ListView mCoachListView;
    private CoachReviewListAdapter mAdapter;
    private List<Coach> mCoachList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach);

        // 记录打开教练点评次数
        int count = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_OPEN_COACH_COUNT);
        PreferencesUtil.write(this, GlobalConstant.SHARE_KEY_OPEN_COACH_COUNT, ++count);

        mTitle = (TextView) findViewById(R.id.title_content);
        mTitle.setText("教练点评");

        mGoBack = (LinearLayout) findViewById(R.id.go_back);
        mGoBack.setOnClickListener(this);
        mSearchBox = (EditText) findViewById(R.id.coach_search);

        mCoachListView = (ListView) findViewById(R.id.coache_list);
        mCoachList = MockData.getCoachList();
        mAdapter = new CoachReviewListAdapter(this, mCoachList);
        mCoachListView.setAdapter(mAdapter);
        mCoachListView.setFocusable(true);
        mCoachListView.requestFocus();
        mCoachListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CoachActivity.this, CoachReviewActivity.class);
                intent.putExtra("coach_id", "10001");
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        imm.hideSoftInputFromWindow(mSearchBox.getWindowToken(), 0); //强制隐藏键盘
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
