package com.abt.price.api;

import com.abt.price.bean.news.NewsBean;
import com.abt.price.bean.price.PriceBean;
import com.abt.price.bean.zhihu.ZhihuBean;
import com.abt.price.constant.URLConstant;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * @描述： @RetrofitInterface
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public interface RetrofitInterface {

    //获取“分类中搜索商品”的数据
    @GET(URLConstant.ZHIHU_THEMES)
    Observable<NewsBean> getNewsData();

    @GET(URLConstant.PRICE_ADD_ITEM)
    Observable<PriceBean> getPriceData();

    @GET(URLConstant.PRICE_LOAD_MORE)
    Observable<PriceBean> loadMorePrice();

    @GET(URLConstant.ZHIHU_LATEST_NEWS)
    Observable<ZhihuBean> loadZhihuData();
}
