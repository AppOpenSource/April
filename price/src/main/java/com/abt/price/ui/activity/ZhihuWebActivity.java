package com.abt.price.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.abt.basic.arch.mvvm.viewmodel.IViewModel;
import com.abt.price.R;
import com.abt.price.databinding.ActivityWebViewBinding;
import com.abt.price.ui.IZhihuWebView;
import com.abt.price.ui.viewmodel.ZhihuWebVM;

/**
 * @描述： @show html with code
 * @作者： @黄卫旗
 * @创建时间： @10/06/2018
 */
public class ZhihuWebActivity extends AppCompatActivity implements IZhihuWebView {

    private static final String ID = "id";
    private String id;

    private Context mContext;
    private ActivityWebViewBinding binding;
    private ZhihuWebVM zhihuWebVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_web_view);
        mContext = this;
        zhihuWebVM = new ZhihuWebVM(this);
    }

    private void parseIntent(){
        id = getIntent().getStringExtra(ID);
    }

    public static Intent newIntent(Context context, String id){
        Intent intent = new Intent(context,ZhihuWebActivity.class);
        intent.putExtra(ZhihuWebActivity.ID,id);
        return intent;
    }

    @Override
    public void loadStart(int i) {

    }

    @Override
    public void loadComplete() {

    }

    @Override
    public void loadFailure(String s) {

    }

    @Override
    public void setViewModel(IViewModel iViewModel) {

    }

    @Override
    public void setToolbarViewModel(Object o) {

    }
}
