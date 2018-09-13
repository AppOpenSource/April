package com.abt.price.model.zhihu;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.core.bean.zhihu.News;
import com.abt.price.core.bean.zhihu.Stories;

/**
 * @描述： @IZhihuModel
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public interface IZhihuModel {
    /**
     * 获取最新的知乎数据
     * @param page 页数
     * @param loadListener
     */
    void getLatestNews(int page, BaseLoadListener<Stories> loadListener);
    /**
     * 获取更多的知乎数据
     * @param page 页数
     * @param loadListener
     */
    void getBeforeNews(int page, BaseLoadListener<Stories> loadListener);
    /**
     * 获取最新的知乎数据
     * @param id newsId
     * @param loadListener
     */
    void getDetailNews(String id, BaseLoadListener<News> loadListener);
}
