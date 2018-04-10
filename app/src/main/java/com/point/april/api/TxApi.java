package com.point.april.api;

import com.point.april.bean.TxWeixinResponse;
import com.point.april.global.GlobalConstant;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by 蔡小木 on 2016/3/4 0004.
 */
public interface TxApi {
    @GET("/wxnew/?key=" + GlobalConstant.TX_APP_KEY + "&num=20")
    Observable<TxWeixinResponse> getWeixin(@Query("page") int page);
}