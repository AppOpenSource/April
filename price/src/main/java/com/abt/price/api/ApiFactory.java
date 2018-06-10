package com.abt.price.api;

/**
 * @描述： @Singleton Factory with retrofit
 * @作者： @黄卫旗
 * @创建时间： @10/06/2018
 */
public class ApiFactory {

    protected static final Object monitor = new Object();
    static ZhihuApi zhihuApiSingleton = null;
    static GankApi gankApiSingleton = null;
    static PriceApi priceApiSingleton = null;

    public static ZhihuApi getZhihuApiSingleton() {
        synchronized (monitor) {
            if (zhihuApiSingleton == null) {
                zhihuApiSingleton = new ApiRetrofit().getZhihuApiService();
            }
            return zhihuApiSingleton;
        }
    }

    public static GankApi getGankApiSingleton() {
        synchronized (monitor) {
            if (gankApiSingleton == null) {
                gankApiSingleton = new ApiRetrofit().getGankApiService();
            }
            return gankApiSingleton;
        }
    }

    public static PriceApi getPriceSingleton() {
        synchronized (monitor) {
            if (priceApiSingleton == null) {
                priceApiSingleton = new ApiRetrofit().getPriceApiService();
            }
            return priceApiSingleton;
        }
    }

}
