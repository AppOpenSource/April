package com.point.april.ui.activity.news;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.point.april.R;
import com.point.april.network.NetworkManager;
import com.point.april.ui.UIConstant;
import com.point.april.ui.adapter.CommunityMsgAdapter;
import com.point.april.ui.adapter.ImagePageAdapter;
import com.point.base.ui.BaseActivity;

import java.util.ArrayList;

/**
 * 暂时没有到
 */
public class PhotoActivity extends BaseActivity implements OnClickListener {

	private RelativeLayout mPhotoRelativeLayout;
	private ViewPager mViewPager;
	private ImagePageAdapter mAdapter;
	private TextView mTvPicNum;
	private Button mBtExit, mBtDel;
	private ArrayList<View> mListViews = null;
	public int mMax=0;
	public int mPostion;
	public boolean isHome;

	private TextView mTitle;
	private TextView mAppCount;
	private LinearLayout mGoBack;

	@Override
	protected void initVariables() {
		Intent intent = getIntent();
		mPostion = intent.getIntExtra(UIConstant.POSITION, 0);
		mMax= CommunityMsgAdapter.mPicUrl.size();

		if (CommunityMsgAdapter.mPicUrl != null) {
			for (int i = 0; i < mMax; i++) {
				initPicUrl(CommunityMsgAdapter.mPicUrl.get(i));
			}
		}
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_photo;
	}

	@Override
	protected void initView() {
		mPhotoRelativeLayout = (RelativeLayout) findViewById(R.id.photo_relativeLayout);
		//mBtExit = (Button) findViewById(R.id.photo_bt_exit);
		//mBtExit.setOnClickListener(this);
		mBtDel = (Button) findViewById(R.id.btn);
		mBtDel.setBackgroundResource(R.drawable.bt_delete);
		mBtDel.setOnClickListener(this);

		mViewPager = (ViewPager) findViewById(R.id.viewpager);
		mViewPager.setOnPageChangeListener(mPageChangeListener);

		//mTvPicNum = (TextView) findViewById(R.id.tv_pic_num);
		mTvPicNum = (TextView) findViewById(R.id.title_content);
		//mTvPicNum.setText("应用数据统计");

		mGoBack = (LinearLayout) findViewById(R.id.go_back);
		mGoBack.setOnClickListener(this);

		initData();
	}

	private void initPicUrl(String url) {
		if (mListViews == null)
			mListViews = new ArrayList<View>();
		ImageView img = new ImageView(this);// 构造textView对象
		img.setBackgroundColor(0xff000000);
		NetworkManager.getInstace().loadImg(img, url);
		img.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT));
		mListViews.add(img);// 添加view
	}

	private void initData() {
		mPhotoRelativeLayout.setBackgroundColor(0x70000000);
		mTvPicNum.setText("1/" + mMax);
		mBtDel.setVisibility(View.GONE);
		mAdapter = new ImagePageAdapter(mListViews);// 构造adapter
		mViewPager.setAdapter(mAdapter);// 设置适配器
		mViewPager.setCurrentItem(mPostion);
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.go_back:
			finish();
			break;
		default:
			break;
		}
	}
	private OnPageChangeListener mPageChangeListener = new OnPageChangeListener() {

		public void onPageSelected(int arg0) {// 页面选择响应函数
			mPostion = arg0;
			int imageNum = arg0 + 1;
			mTvPicNum.setText(imageNum + "/" + mMax);
		}

		public void onPageScrolled(int arg0, float arg1, int arg2) {// 滑动中。。。

		}

		public void onPageScrollStateChanged(int arg0) {// 滑动状态改变

		}
	};
}
