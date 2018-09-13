package com.abt.price.api;

import com.abt.price.core.bean.price.PriceBean;

import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @描述： @get Price with retrofit
 * @作者： @黄卫旗
 * @创建时间： @10/06/2018
 */
public interface PriceApi {

    @GET("price/add")
    io.reactivex.Observable<PriceBean> getPriceData();

    @GET("price/loadmore/{page}")
    io.reactivex.Observable<PriceBean> loadMorePrice(@Path("page") int page);

}
