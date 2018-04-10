package com.point.april.ui.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import com.point.april.ui.adapter.CommunityPhotoAdapter;
import com.point.april.widget.image.BitmapUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuz on 16/6/3.
 */
public class FeedGridView extends BaseGridView {


	private List<String> mDatas = new ArrayList<String>();

	public FeedGridView(Context context) {
		super(context);
	}

	public FeedGridView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public FeedGridView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	private CommunityPhotoAdapter photoAdapter;
	private int mColumnNum;

	public void setPhotoAdapter(List<String> imgs) {
		mDatas.clear();
		mDatas.addAll(imgs);
		for (int i = 0; i < mDatas.size(); i++) {
			if(TextUtils.isEmpty(mDatas.get(i))){
				mDatas.remove(i);
			}
		}
		int count = mDatas.size();
		//一张图片的时候也是占1/2
		if(count == 1 ){
			mColumnNum = 1;
			setNumColumns(1);
		}else if (count == 2 || count == 4) {
			mColumnNum = 2;
			setNumColumns(2);
		} else {
			mColumnNum = 3;
			setNumColumns(3);
		}
		int width = calculateColumnWidth();
		setColumnWidth(width);
		photoAdapter = new CommunityPhotoAdapter((Activity) getContext(), mDatas, width);
		this.setAdapter(photoAdapter);
		photoAdapter.notifyDataSetChanged();
		setGridViewWidthBasedOnChildren(this,count);
	}

	private int calculateColumnWidth() {
		int width = BitmapUtil.getScreenWidth((Activity) getContext());
		if(mColumnNum==1){
			width = (width - BitmapUtil.Dp2Px(getContext(), 10 * 2 + 10)) / 2;
		}else if (mColumnNum == 2) {
			width = (width - BitmapUtil.Dp2Px(getContext(), 10 * 2 + 10)) / 3;
		} else if (mColumnNum == 3) {
			width = (width - BitmapUtil.Dp2Px(getContext(), 10 * 2 + 10 * 2)) / 3;
		}
		return width;
	}

	/**
	 * 动态计算gridview的宽度
	 *
	 * @param gridView
	 * @param count
	 */
	public static void setGridViewWidthBasedOnChildren(GridView gridView, int count) {
		// 获取gridview的adapter
		ListAdapter listAdapter = gridView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		// 固定列宽，有多少列
		int col = count;
		if (listAdapter.getCount() < count) {
			col = listAdapter.getCount();
		}
		if (count == 4) {
			col = 2;
		}
		int totalWidth = 0;
		for (int i = 0; i < col; i++) {
			// 获取gridview的每一个item
			View listItem = listAdapter.getView(i, null, gridView);
			listItem.measure(0, 0);
			// 获取item的宽度和
			totalWidth += listItem.getMeasuredWidth() + 10 * 2;
		}
		// 获取gridview的布局参数
		ViewGroup.LayoutParams params = gridView.getLayoutParams();
		// 设置宽度
		params.width = totalWidth;
		// 设置参数
		gridView.setLayoutParams(params);
	}

}
