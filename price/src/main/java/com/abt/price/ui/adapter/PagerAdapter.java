package com.abt.price.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @描述： @PagerAdapter
 * @作者： @黄卫旗
 * @创建时间： @11/06/2018
 */
public class PagerAdapter extends FragmentPagerAdapter {

    private String tag;
    private List<Fragment> fragmentList;

    public PagerAdapter(FragmentManager supportFragmentManager, List<Fragment> fragmentList, String tag) {
        super(supportFragmentManager);
        this.fragmentList = fragmentList;
        this.tag = tag;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        if (fragmentList != null) {
            return fragmentList.size();
        }
        return 0;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (tag.equals("main_view_pager")) {
            switch (position) {
                case 0:
                    return "Price";
                case 1:
                    return "Gank";
            }
        }
        return null;
    }
}
