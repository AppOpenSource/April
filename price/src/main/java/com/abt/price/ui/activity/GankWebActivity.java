package com.abt.price.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.abt.price.R;
import com.abt.price.base.BaseActivity;
import com.abt.price.databinding.ActivityGankWebBinding;
import com.abt.price.ui.IGankWebView;
import com.abt.price.ui.viewmodel.GankWebVM;

/**
 * @描述： @GankWebActivity
 * @作者： @黄卫旗
 * @创建时间： @11/06/2018
 */
public class GankWebActivity extends BaseActivity implements IGankWebView {

    public static final String GANK_URL = "gankUrl";

    private String gankUrl;
    private ActivityGankWebBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gank_web);
        GankWebVM gankWebVM = new GankWebVM(this, this);
        gankWebVM.loadUrl(gankUrl);
    }

    public static Intent newIntent(Context context, String url) {
        Intent intent = new Intent(context, GankWebActivity.class);
        intent.putExtra(GankWebActivity.GANK_URL, url);
        return intent;
    }

    /**
     * 得到Intent传递的数据
     */
    private void parseIntent() {
        gankUrl = getIntent().getStringExtra(GANK_URL);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.urlWeb.destroy();
    }

    @Override
    public ProgressBar getProgressBar() {
        return binding.pbProgress;
    }

    @Override
    public WebView getWebView() {
        return binding.urlWeb;
    }
}
