package com.point.base.ui;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.point.base.control.logcat.Logcat;
import com.point.base.model.control.BasePresenter;
import com.point.base.model.control.BaseView;
import com.point.base.model.control.LogicProxy;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();
    protected BasePresenter mPresenter;
    //private LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logcat.d("Activity Location (%s.java:0)", getClass().getSimpleName());
        initVariables();
        setContentView(getLayoutResource());
        initView();
        loadData();
        //ButterKnife.bind(this);
        //mLoadingView = new LoadingView(this);
    }

    protected void initVariables() {
        Logcat.d(TAG, "VERSION.SDK_INT is:"+ Build.VERSION.SDK_INT);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(*//*View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | *//*View.SYSTEM_UI_FLAG_IMMERSIVE
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            Logcat.d(TAG, "VERSION_CODES.LOLLIPOP is:"+ Build.VERSION_CODES.LOLLIPOP);
        }

        Logcat.d(TAG, "android.os.Build.MANUFACTURER is:"+android.os.Build.MANUFACTURER);
        if (android.os.Build.MANUFACTURER.equals("HUAWEI")) {
            Window window = getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }*/
    }

    protected abstract int getLayoutResource();

    protected abstract void initView();

    protected abstract void loadData();

    //获得该页面的实例
    public <T> T getLogicImpl(Class cls, BaseView o) {
        return LogicProxy.getInstance().bind(cls, o);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        // 打开Activity动画
    }

    @Override
    public void finish() {
        super.finish();
        // 关闭动画
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //ButterKnife.unbind(this);
        if (mPresenter != null)
            mPresenter.detachView();
    }

    public void showLoadView() {
        // mLoadingView.show();
    }

    public void hideLoadView() {
        //  mLoadingView.hide();
    }
}
