package com.abt.price.api;

import com.abt.price.bean.gank.GankData;
import com.abt.price.bean.gank.Meizhi;
import com.abt.price.bean.gank.Video;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @描述： @get Gank with retrofit
 * @作者： @黄卫旗
 * @创建时间： @10/06/2018
 */
public interface GankApi {

    @GET("data/福利/10/{page}")
    Observable<Meizhi> getMeizhiData(@Path("page") int page);

    @GET("day/{year}/{month}/{day}")
    Observable<GankData> getGankData(@Path("year") int year, @Path("month") int month, @Path("day") int day);

    @GET("data/休息视频/10/{page}")
    Observable<Video> getVideoData(@Path("page") int page);
}
