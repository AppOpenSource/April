package com.abt.price.model.zhihu;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.bean.zhihu.SimpleZhihuBean;

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
    void loadZhihuData(int page, BaseLoadListener<SimpleZhihuBean> loadListener);
}
