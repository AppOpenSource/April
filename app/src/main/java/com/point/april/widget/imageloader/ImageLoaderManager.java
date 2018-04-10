package com.point.april.widget.imageloader;

import android.content.Context;
import android.widget.ImageView;

public class ImageLoaderManager {

	private int mType;				// 图片类型(大 中 小)
	private int mPlaceHolder; 		// 默认图片
	private int mWifiStrategy;		// 加载策略，是否在wifi下才加载
	
	private String mUrl;
	private ImageView mImgView;

	private static ImageLoaderManager mManager;

	public static ImageLoaderManager getInstance() {
		if (null == mManager) {
			mManager = new ImageLoaderManager(null);
		}
		return mManager;
	}

	public void init(Context context) {
		new UniversalImageLoader(context);
	}

	public void loadImg(String url, ImageView view) {
		UniversalImageLoader.loadImg(url, view);
	}

	private ImageLoaderManager(Builder builder) {
		/*this.type = builder.type;
		this.url = builder.url;
		this.placeHolder = builder.placeHolder;
		this.imgView = builder.imgView;
		this.wifiStrategy = builder.wifiStrategy;*/
	}

	public int getType() {
		return mType;
	}

	public String getUrl() {
		return mUrl;
	}

	public int getPlaceHolder() {
		return mPlaceHolder;
	}

	public ImageView getImgView() {
		return mImgView;
	}

	public int getWifiStrategy() {
		return mWifiStrategy;
	}

	public static class Builder {
		private int type;
		private String url;
		private int placeHolder;
		private ImageView imgView;
		private int wifiStrategy;

		public Builder() {
			this.type = ImageLoaderUtil.PIC_SMALL;
			this.url = "";
			/// this.placeHolder = R.drawable.default_pic_big;
			this.imgView = null;
			this.wifiStrategy = ImageLoaderUtil.LOAD_STRATEGY_NORMAL;
		}

		public Builder type(int type) {
			this.type = type;
			return this;
		}

		public Builder url(String url) {
			this.url = url;
			return this;
		}

		public Builder placeHolder(int placeHolder) {
			this.placeHolder = placeHolder;
			return this;
		}

		public Builder imgView(ImageView imgView) {
			this.imgView = imgView;
			return this;
		}

		public Builder strategy(int strategy) {
			this.wifiStrategy = strategy;
			return this;
		}

		public ImageLoaderManager build() {
			return new ImageLoaderManager(this);
		}

	}
}
