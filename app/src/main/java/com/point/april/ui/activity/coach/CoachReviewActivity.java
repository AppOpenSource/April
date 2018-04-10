package com.point.april.ui.activity.coach;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.point.april.R;

/**
 * 点评教练页面
 */
public class CoachReviewActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTitle;
    private EditText mCommentView;
    private Button mSend;
    private LinearLayout mGoBack;
    private CheckBox mStar1 = null;
    private CheckBox mStar2 = null;
    private CheckBox mStar3 = null;
    private CheckBox mStar4 = null;
    private CheckBox mStar5 = null;
    private TextView mDes = null;
    private int mTotolStar;
    private String mCommentDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_review);

        mTitle = (TextView) findViewById(R.id.title_content);
        mTitle.setText("教练点评");
        mSend = (Button) findViewById(R.id.btn);
        mSend.setVisibility(View.VISIBLE);
        mSend.setText("发送");
        mSend.setOnClickListener(this);

        mGoBack = (LinearLayout) findViewById(R.id.go_back);
        mGoBack.setOnClickListener(this);

        mStar1 = (CheckBox) findViewById(R.id.star1);
        mStar2 = (CheckBox) findViewById(R.id.star2);
        mStar3 = (CheckBox) findViewById(R.id.star3);
        mStar4 = (CheckBox) findViewById(R.id.star4);
        mStar5 = (CheckBox) findViewById(R.id.star5);
        mStar1.setOnClickListener(this);
        mStar2.setOnClickListener(this);
        mStar3.setOnClickListener(this);
        mStar4.setOnClickListener(this);
        mStar5.setOnClickListener(this);
        mDes = (TextView) findViewById(R.id.des);

        mCommentView = (EditText) findViewById(R.id.comment_detail);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                this.finish();
                break;
            case R.id.btn:
                // 总体评价 XXX
                // mTotolStar;
                // 具体描述 YYY
                mCommentDetail = mCommentView.getText().toString();
                // send(xxx, YYY); // TODO
                if (mTotolStar <=0 || TextUtils.isEmpty(mCommentDetail)) {
                    Toast.makeText(CoachReviewActivity.this, "请先评价", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(CoachReviewActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                if (mSend != null) {
                    mSend.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            CoachReviewActivity.this.finish();
                        }
                    }, 1*1000);
                }
                break;
            case R.id.star1:
                mStar1.setChecked(true);
                mStar2.setChecked(false);
                mStar3.setChecked(false);
                mStar4.setChecked(false);
                mStar5.setChecked(false);
                mDes.setText("1星");
                mTotolStar = 1;
                break;
            case R.id.star2:
                mStar1.setChecked(true);
                mStar2.setChecked(true);
                mStar3.setChecked(false);
                mStar4.setChecked(false);
                mStar5.setChecked(false);
                mDes.setText("2星");
                mTotolStar = 2;
                break;
            case R.id.star3:
                mStar1.setChecked(true);
                mStar2.setChecked(true);
                mStar3.setChecked(true);
                mStar4.setChecked(false);
                mStar5.setChecked(false);
                mDes.setText("3星");
                mTotolStar = 3;
                break;
            case R.id.star4:
                mStar1.setChecked(true);
                mStar2.setChecked(true);
                mStar3.setChecked(true);
                mStar4.setChecked(true);
                mStar5.setChecked(false);
                mDes.setText("4星");
                mTotolStar = 4;
                break;
            case R.id.star5:
                mStar1.setChecked(true);
                mStar2.setChecked(true);
                mStar3.setChecked(true);
                mStar4.setChecked(true);
                mStar5.setChecked(true);
                mDes.setText("5星");
                mTotolStar = 5;
                break;
        }
    }
}
