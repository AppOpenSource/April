package com.point.april.widget.imageloader;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.point.april.common.log.LogManager;
import com.point.april.util.ScreenUtils;
import com.point.april.R;

public class ImageLoaderUtil {

    public static final int PIC_LARGE = 0;
    public static final int PIC_MEDIUM = 1;
    public static final int PIC_SMALL = 2;

    public static final int LOAD_STRATEGY_NORMAL = 0;
    public static final int LOAD_STRATEGY_ONLY_WIFI = 1;
    
	private static int LOADER_STATUS = 1; // 加载器状态。1:开启；0:关闭
	private static int STATUS_OPENED = 1;
	private static int STATUS_CLOSED = 0;

	private static boolean wifiEnable = true;
	private static final String SAVE_NAME = "LoaderUtils";
	private static final String SAVE_KEY = "LoaderStatus";

	private static final int MAX_IMAGE_MEMORY_CACHE_SIZE = 2 * 1024 * 1024; // 2MB
	private static final int MAX_IMAGE_DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
	private static final int MAX_IMAGE_DISK_FILE_COUNT = 200;

    private static BaseImageLoaderStrategy mStrategy;
    private ImageLoaderUtil mImgLoader;
    
    public ImageLoaderUtil getInstance() {
    	if (mImgLoader == null) {
    		mImgLoader = new ImageLoaderUtil();
    	}
    	return mImgLoader;
    }
    
	/**
	 * 图片圆角像素
	 */
	private static final int IMAGE_CORNER_RADIUS_PIXELS = 360;

	public static void init(Context context) {
		ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(context));
		// LOADER_STATUS = PreferencesUtils.getInt(context, SAVE_NAME, SAVE_KEY, STATUS_OPENED);
		
		mStrategy = new UniversalImageLoaderStrategy();
	}

	/**
	 * 设置wifi是否可用
	 */
	public static void setWifiEnable(boolean b) {
		wifiEnable = b;
	}

	/**
	 * 加载器是否打开。
	 */
	public static boolean isOpen() {
		return LOADER_STATUS == STATUS_OPENED;
	}

	/**
	 * 显示图片
	 */
	public static void display(String url, ImageView iv, DisplayImageOptions mOptions) {
		if (wifiEnable || LOADER_STATUS == STATUS_OPENED) {
			if (ImageLoader.getInstance().isInited())
				ImageLoader.getInstance().displayImage(url, iv, mOptions);
		}
	}

	public static void display(String url, ImageView iv, DisplayImageOptions mOptions, ImageLoadingListener listener) {
		if (wifiEnable || LOADER_STATUS == STATUS_OPENED) {
			if (ImageLoader.getInstance().isInited())
				ImageLoader.getInstance().displayImage(url, iv, mOptions, listener);
		}
	}

	public static void display(String url, ImageView iv) {
		if (wifiEnable || LOADER_STATUS == STATUS_OPENED) {
			if (ImageLoader.getInstance().isInited())
				ImageLoader.getInstance().displayImage(url, iv, createOptions(R.drawable.aa_unloginheadpic));
		}
	}

	public static void display(int imageResid,ImageView imageview){
		ImageLoader.getInstance().displayImage("drawable://" + imageResid,
				imageview);
	}

	/**
	 * 关闭图片加载
	 */
	public static void closeLoader(Context context) {
		LOADER_STATUS = STATUS_CLOSED;
		// PreferencesUtils.putInt(context, SAVE_NAME, SAVE_KEY, STATUS_CLOSED);
	}

	/**
	 * 开启图片加载
	 */
	public static void openLoader(Context context) {
		LOADER_STATUS = STATUS_OPENED;
		// PreferencesUtils.putInt(context, SAVE_NAME, SAVE_KEY, STATUS_OPENED);
	}

	public static DisplayImageOptions createOptions(int imgLoading) {
		return new DisplayImageOptions.Builder().bitmapConfig(Bitmap.Config.RGB_565)
				.showImageOnLoading(imgLoading).cacheInMemory(true).cacheOnDisk(true).build();
	}

	public static DisplayImageOptions createOptions(int imgLoading, int radiusPixels) {
		return new DisplayImageOptions.Builder().bitmapConfig(Bitmap.Config.RGB_565)
				.showImageOnLoading(imgLoading).imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
				.cacheInMemory(true).displayer(new RoundedBitmapDisplayer(radiusPixels))
				.cacheOnDisk(true).build();
	}

	public static void pause(){
		if (wifiEnable || LOADER_STATUS == STATUS_OPENED) {
			if (ImageLoader.getInstance().isInited())
				ImageLoader.getInstance().pause();
		}
	}

	public static void resume(){
		if (wifiEnable || LOADER_STATUS == STATUS_OPENED) {
			if (ImageLoader.getInstance().isInited())
				ImageLoader.getInstance().resume();
		}
	}

	/**
	 *  初始化ImageLoader(只在Application的OnCreate中调用一次就行，其他时候不调)
	 */
	public static ImageLoaderConfiguration initImageLoader(Context context, String dirName) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
		.memoryCacheExtraOptions(ScreenUtils.getScreenWidth(context), ScreenUtils.getScreenHeight(context))
		.threadPriority(Thread.NORM_PRIORITY - 2)
		.threadPoolSize(3)
		.memoryCacheSize(getMemoryCacheSize(context))
		.denyCacheImageMultipleSizesInMemory()
		.diskCacheFileNameGenerator(new Md5FileNameGenerator())
		.diskCacheSize(MAX_IMAGE_DISK_CACHE_SIZE)
		.diskCacheFileCount(MAX_IMAGE_DISK_FILE_COUNT)
		.tasksProcessingOrder(QueueProcessingType.LIFO)
		.writeDebugLogs()
		.build();
		return config;
	}

	/**
	 * 得到内存缓存的大小
	 */
	private static int getMemoryCacheSize(Context context) {
		int memoryCacheSize;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
			int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();
			memoryCacheSize = (memClass / 8) * 1024 * 1024; // 1/8 of app memory
			LogManager.v(LogManager.__FILE__(), "-memoryCacheSize--" + memoryCacheSize);
		} else {
			memoryCacheSize = MAX_IMAGE_MEMORY_CACHE_SIZE;
			LogManager.v(LogManager.__FILE__(), "-memoryCacheSize--" + memoryCacheSize);
		}
		return memoryCacheSize;
	}

	/**
	 * 显示常规的图片
	 * @param imageResId
	 *            默认图片资源ID
	 * @return DisplayImageOptions
	 */
	public static DisplayImageOptions getDisplayImageOptions(int imageResId) {
		return getDisplayImageOptions(imageResId, false, false, true,0);
	}

	/**
	 * 显示圆角的图片
	 * @param imageResId
	 * @param imageCorner 圆角的弧度
	 * @return
	 */
	public static DisplayImageOptions getRoundDisplayImageOptions(int imageResId,int imageCorner) {
		return getDisplayImageOptions(imageResId, false, true, true,imageCorner);
	}

	/**
	 * 显示圆形的图片
	 * @param imageResId
	 *            默认图片资源ID
	 * @return DisplayImageOptions
	 */
	public static DisplayImageOptions getRoundDisplayImageOptions(int imageResId) {
		return getDisplayImageOptions(imageResId, false, true, true,IMAGE_CORNER_RADIUS_PIXELS);
	}

	/**
	 * 不显示加载中的默认图片
	 *
	 * @return DisplayImageOptions
	 */
	public static DisplayImageOptions getNotShowDisplayImageOptions() {
		return getDisplayImageOptions(0, false, false, true,0);
	}

	/**
	 * 显示res资源文件中的图片
	 * @return
	 */
	public static DisplayImageOptions getResImageOptions() {
		return getDisplayImageOptions(0, true, false, true,0);
	}

	/**
	 * 图片资源ID
	 * @param imageResId
	 * @param cornerRadiusPixels
	 *            显示图片是否为圆形
	 * @param resetViewBeforeLoading
	 *            是否需要加载前重置视图
	 * @return DisplayImageOptions
	 */
	private static DisplayImageOptions getDisplayImageOptions(int imageResId, boolean showResImage, boolean cornerRadiusPixels, boolean resetViewBeforeLoading,int imageCorner) {
		DisplayImageOptions.Builder builder = new DisplayImageOptions.Builder();

		builder.cacheInMemory(true);
		if(showResImage){
			builder.cacheOnDisk(false);
		}else{
			builder.cacheOnDisk(true);
		}
		builder.bitmapConfig(Bitmap.Config.RGB_565);
		builder.imageScaleType(ImageScaleType.EXACTLY);
		if (cornerRadiusPixels) {
			builder.displayer(new RoundedBitmapDisplayer(imageCorner));
		}
		if (0 != imageResId) {
			builder.showImageOnLoading(imageResId);
			builder.showImageForEmptyUri(imageResId);
			builder.showImageOnFail(imageResId);
		}
		builder.resetViewBeforeLoading(resetViewBeforeLoading);
		return builder.build();
	}

	/**
	 * 清除ImageLoade内存以及硬盘中的缓存
	 */
	public static void clearCache() {
		ImageLoader.getInstance().clearMemoryCache();
		ImageLoader.getInstance().clearDiskCache();
	}

	/**
	 * 清除ImageLoade内存中的缓存
	 */
	public static void clearMemoryCache() {
		ImageLoader.getInstance().clearMemoryCache();
	}

	/**
	 * 清除ImageLoade硬盘中的缓存
	 */
	public static void clearDiskCache() {
		ImageLoader.getInstance().clearDiskCache();
	}

	/**
	 * 设置加载图片的策略
	 * @param strategy
	 */
    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy) {
        mStrategy =strategy;
    }
    
    public void loadImage(Context context, ImageLoaderManager img) {
        mStrategy.loadImage(context, img);
    }
}
