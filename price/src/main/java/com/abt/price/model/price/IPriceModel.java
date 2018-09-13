package com.abt.price.model.price;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.core.bean.price.SimplePriceBean;

/**
 * @描述： @IPriceModel
 * @作者： @黄卫旗
 * @创建时间： @08/06/2018
 */
public interface IPriceModel {

    /**
     * 获取价格数据
     * @param page 页数
     * @param loadListener
     */
    void loadPriceData(int page, BaseLoadListener<SimplePriceBean> loadListener);
}
