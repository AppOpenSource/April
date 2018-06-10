package com.abt.price.http;

import com.abt.price.bean.NewsBean;
import com.abt.price.bean.PriceBean;
import com.abt.price.bean.ZhihuBean;
import com.abt.price.constant.URLConstant;
import com.abt.price.retrofitinterface.RetrofitInterface;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @描述： @HttpUtils
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class HttpUtils {

    private static final int DEFAULT_TIMEOUT = 8; //连接 超时的时间，单位：秒
    private static Retrofit retrofit;
    private static RetrofitInterface retrofitInterface;
    private static final OkHttpClient client = new OkHttpClient.Builder().
            connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
            readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).
            writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS).build();

    private synchronized static RetrofitInterface getRetrofit() {
        //初始化retrofit的配置
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URLConstant.PRICE_BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
            retrofitInterface = retrofit.create(RetrofitInterface.class);
        }
        return retrofitInterface;
    }

    //获取新闻数据
    public static io.reactivex.Observable<NewsBean> getNewsData() {
        return getRetrofit().getNewsData();
    }

    //获取Price数据
    public static io.reactivex.Observable<PriceBean> getPriceData() {
        return getRetrofit().getPriceData();
    }

    public static io.reactivex.Observable<PriceBean> loadMorePrice() {
        return getRetrofit().loadMorePrice();
    }

    //获取知乎数据
    public static io.reactivex.Observable<ZhihuBean> getZhihuData() {
        return getRetrofit().loadZhihuData();
    }
}
