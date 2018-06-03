package com.point.april.ui.activity.screensaver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.point.april.R;
import com.point.april.ui.activity.AprilActivity;

/**
 * APP密码输入页面
 */
public class DisplayInputActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = DisplayInputActivity.class.getSimpleName();
    private TextView mText1;
    private TextView mText2;
    private TextView mText3;
    private TextView mText4;

    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;
    private Button mBtn4;
    private Button mBtn5;
    private Button mBtn6;

    private Button mBtn7;
    private Button mBtn8;
    private Button mBtn9;
    private Button mBtn0;
    private Button mBtnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_input);

        mText1 = (TextView) findViewById(R.id.text1);
        mText2 = (TextView) findViewById(R.id.text2);
        mText3 = (TextView) findViewById(R.id.text3);
        mText4 = (TextView) findViewById(R.id.text4);

        mBtn1 = (Button) findViewById(R.id.btn1);
        mBtn2 = (Button) findViewById(R.id.btn2);
        mBtn3 = (Button) findViewById(R.id.btn3);

        mBtn4 = (Button) findViewById(R.id.btn4);
        mBtn5 = (Button) findViewById(R.id.btn5);
        mBtn6 = (Button) findViewById(R.id.btn6);

        mBtn7 = (Button) findViewById(R.id.btn7);
        mBtn8 = (Button) findViewById(R.id.btn8);
        mBtn9 = (Button) findViewById(R.id.btn9);

        mBtn0 = (Button) findViewById(R.id.btn0);
        mBtnCancel = (Button) findViewById(R.id.cancel_btn);

        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
        mBtn4.setOnClickListener(this);
        mBtn5.setOnClickListener(this);
        mBtn6.setOnClickListener(this);
        mBtn7.setOnClickListener(this);
        mBtn8.setOnClickListener(this);
        mBtn9.setOnClickListener(this);
        mBtn0.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private int mCount = 0;
    private StringBuilder mRes = new StringBuilder();

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                mCount++;
                mRes.append("1");
            break;
            case R.id.btn2:
                mCount++;
                mRes.append("2");
                break;
            case R.id.btn3:
                mCount++;
                mRes.append("3");
                break;
            case R.id.btn4:
                mCount++;
                mRes.append("4");
                break;
            case R.id.btn5:
                mCount++;
                mRes.append("5");
                break;
            case R.id.btn6:
                mCount++;
                mRes.append("6");
                break;
            case R.id.btn7:
                mCount++;
                mRes.append("7");
                break;
            case R.id.btn8:
                mCount++;
                mRes.append("8");
                break;
            case R.id.btn9:
                mCount++;
                mRes.append("9");
                break;
            case R.id.btn0:
                mCount++;
                mRes.append("0");
                break;
            case R.id.cancel_btn: {
                if (mCount > 0) {
                    mRes.deleteCharAt(mCount-1);
                    mCount = mCount - 1;
                } else {
                    this.finish();
                }
                break;
            }
        }

        if (mCount > 0) {
            mBtnCancel.setText("删除");
        } else {
            mBtnCancel.setText("取消");
        }

        switch (mCount) {
            case 0:
                mText1.setText("");
                mText2.setText("");
                mText3.setText("");
                mText4.setText("");
                break;
            case 1:
                mText1.setText("*");
                mText2.setText("");
                mText3.setText("");
                mText4.setText("");
                break;
            case 2:
                mText1.setText("*");
                mText2.setText("*");
                mText3.setText("");
                mText4.setText("");
                break;
            case 3:
                mText1.setText("*");
                mText2.setText("*");
                mText3.setText("*");
                mText4.setText("");
                break;
            case 4:
                mText1.setText("*");
                mText2.setText("*");
                mText3.setText("*");
                mText4.setText("*");
                break;
        }
        if (mRes != null && mRes.toString().equalsIgnoreCase("0721")) {
            Intent intent = new Intent(DisplayInputActivity.this, AprilActivity.class);
            startActivity(intent);
            this.finish();
        }
    }
}
