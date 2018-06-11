package com.abt.price.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;

import com.abt.price.R;
import com.abt.price.base.BaseActivity;
import com.abt.price.databinding.ActivityMainBinding;
import com.abt.price.ui.adapter.PagerAdapter;
import com.abt.price.ui.fragment.GankFragment;
import com.abt.price.ui.fragment.PriceFragment;
import com.abt.price.ui.fragment.ZhihuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述： @MainActivity
 * @作者： @黄卫旗
 * @创建时间： @11/06/2018
 */
public class MainActivity extends BaseActivity {

    private List<Fragment> fragmentList;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initTabView();
    }

    //初始化Tab滑动
    public void initTabView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new PriceFragment());
        fragmentList.add(new GankFragment());
        fragmentList.add(new ZhihuFragment());
        binding.viewPager.setOffscreenPageLimit(3); // 设置至少3个fragment，防止重复创建和销毁，造成内存溢出
        binding.viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager(), fragmentList, "main_view_pager"));//给ViewPager设置适配器
        binding.tabLayout.setupWithViewPager(binding.viewPager);//将TabLayout和ViewPager关联起来
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.today_github) {
            String githubTrending = "https://github.com/trending";
            startActivity(GankWebActivity.newIntent(this, githubTrending));
            return true;
        } else if (item.getItemId() == R.id.about_me) {
            //startActivity(new Intent(this, AboutMeActivity.class));
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
