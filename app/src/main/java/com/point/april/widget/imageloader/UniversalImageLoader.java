package com.point.april.widget.imageloader;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.L;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.point.april.R;

import java.io.File;

public class UniversalImageLoader {

	private Context mContext;
	private String mPath = "UniversalImageLoader/Cache";

	public UniversalImageLoader(Context context) {
		L.disableLogging();//取消log
		this.mContext = context;
		File cacheDir = StorageUtils.getOwnCacheDirectory(mContext, mPath);
		ImageLoader.getInstance().init(new ImageLoaderConfiguration.Builder(mContext)
		// 线程池内加载的数量
		.threadPoolSize(3)
		// 缓存的文件数量
		.diskCacheFileCount(100)
		// 即保存的每个缓存文件的最大长宽
		.memoryCacheExtraOptions(480, 480)
		// 内存大小
		.memoryCacheSize(5 * 1024 * 1024)
		// SD卡大小
		.diskCacheSize(50 * 1024 * 1024)
		// 自定义缓存路径
		.diskCache(new UnlimitedDiskCache(cacheDir))
		// 网路请求超时和读取超时
		.imageDownloader(new BaseImageDownloader(mContext, 5*1000, 5*1000))
		.build());
	}

	public static void loadImg(String url, ImageView view) {
		DisplayImageOptions mOptions = new DisplayImageOptions.Builder()
		.cacheInMemory(true)// 是否写入内存
		.cacheOnDisk(true)// 是否写入SD卡
		.imageScaleType(ImageScaleType.EXACTLY)// 大小成倍缩放
		.showImageOnLoading(R.drawable.loading_animation)// 加载中显示的图片
		.showImageOnFail(R.color.listview_gray)// 加载失败显示的图片
		.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型
		.build();
		ImageLoader.getInstance().displayImage(url, view, mOptions);
	}
}
