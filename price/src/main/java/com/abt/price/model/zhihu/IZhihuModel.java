package com.abt.price.model.zhihu;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.bean.zhihu.Stories;

/**
 * @描述： @IZhihuModel
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public interface IZhihuModel {
    /**
     * 获取知乎数据
     * @param page 页数
     * @param loadListener
     */
    void getLatestNews(int page, BaseLoadListener<Stories> loadListener);

    void getBeforeNews(int page, BaseLoadListener<Stories> loadListener);
}
