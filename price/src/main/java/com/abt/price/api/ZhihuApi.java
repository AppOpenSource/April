package com.abt.price.api;

import com.abt.price.core.bean.zhihu.News;
import com.abt.price.core.bean.zhihu.NewsTimeLine;
import com.abt.price.core.bean.zhihu.SplashImage;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @描述： @get Zhihu with retrofit
 * @作者： @黄卫旗
 * @创建时间： @10/06/2018
 */
public interface ZhihuApi {

    @GET("start-image/1080*1920")
    Observable<SplashImage> getSplashImage();

    @GET("news/latest")
    Observable<NewsTimeLine> getLatestNews();

    @GET("news/before/{time}")
    Observable<NewsTimeLine> getBeforetNews(@Path("time") String time);

    @GET("news/{id}")
    Observable<News> getDetailNews(@Path("id") String id);
}
