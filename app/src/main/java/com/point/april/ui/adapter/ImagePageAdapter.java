package com.point.april.ui.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

public class ImagePageAdapter extends PagerAdapter {

	private ArrayList<View> mListViews;// content
	private int mSize;// 页数

	public ImagePageAdapter(ArrayList<View> listViews) {// 构造函数
		// 初始化viewpager的时候给的一个页面
		this.mListViews = listViews;
		mSize = listViews == null ? 0 : listViews.size();
	}

	public void setListViews(ArrayList<View> listViews) {// 自己写的一个方法用来添加数据
		this.mListViews = listViews;
		mSize = listViews == null ? 0 : listViews.size();
	}

	public int getCount() {// 返回数量
		return mSize;
	}

	public int getItemPosition(Object object) {
		return POSITION_NONE;
	}

	public void destroyItem(View arg0, int arg1, Object arg2) {// 销毁view对象
		((ViewPager) arg0).removeView(mListViews.get(arg1 % mSize));
	}

	public void finishUpdate(View arg0) {
	}

	public Object instantiateItem(View arg0, int arg1) {// 返回view对象
		try {
			((ViewPager) arg0).addView(mListViews.get(arg1 % mSize), 0);

		} catch (Exception e) {
		}
		return mListViews.get(arg1 % mSize);
	}

	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

}
