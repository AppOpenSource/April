package com.abt.price.ui.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.abt.price.R;
import com.abt.price.base.BaseActivity;
import com.abt.price.databinding.ActivityAboutMeBinding;

/**
 * @描述： @AboutMeActivity
 * @作者： @黄卫旗
 * @创建时间： @11/06/2018
 */
public class AboutMeActivity extends BaseActivity implements View.OnClickListener {

    private ActivityAboutMeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about_me);
        //binding.collapsingToolbarLayout.setTitle("很高兴你能看到这里");
        binding.tvGithub.setOnClickListener(this);
        binding.tvBlog.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_github:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(binding.tvGithub.getText().toString()));
                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                startActivity(intent);
                break;
            case R.id.tv_blog:
                Intent blog = new Intent(Intent.ACTION_VIEW, Uri.parse(binding.tvBlog.getText().toString()));
                blog.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                startActivity(blog);
                break;
        }
    }
}
