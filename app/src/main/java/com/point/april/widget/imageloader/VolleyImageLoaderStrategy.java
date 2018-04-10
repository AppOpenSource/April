package com.point.april.widget.imageloader;

import android.content.Context;

public class VolleyImageLoaderStrategy implements BaseImageLoaderStrategy {

	@Override
	public void loadImage(Context ctx, ImageLoaderManager img) {
		/*boolean flag = SettingUtils.getOnlyWifiLoadImg(ctx);
		// 如果不是在wifi下加载图片，直接加载
		if (!flag) {
			loadNormal(ctx, img);
			return;
		}
		int strategy = img.getStrategy();
		if (strategy == ImageLoaderUtil.LOAD_STRATEGY_ONLY_WIFI) {
			int netType = AppUtils.getNetWorkType(AbsApplication.app());
			// 如果是在wifi下才加载图片，并且当前网络是wifi,直接加载
			if (netType == AppUtils.NETWORKTYPE_WIFI) {
				loadNormal(ctx, img);
			} else {
				//如果是在wifi下才加载图片，并且当前网络不是wifi，加载缓存
				loadCache(ctx, img);
			}
		} else {
			//如果不是在wifi下才加载图片
			loadNormal(ctx,img);
		}*/
	}
	
    /**
     * load image with volley
     */
    public void loadNormal(Context ctx, ImageLoaderManager img) {
        // ImageLoaderManager.getInstance().loadImg(url, view);
    	// TODO
    }

    /**
     *load cache image with volley
     */
    public void loadCache(Context ctx, ImageLoaderManager img) {
    	// TODO
    }
	
}
