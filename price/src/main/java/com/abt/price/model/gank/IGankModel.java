package com.abt.price.model.gank;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.bean.gank.Meizhi;

/**
 * @描述： @IGankModel
 * @作者： @黄卫旗
 * @创建时间： @08/06/2018
 */
public interface IGankModel {

    /**
     * 获取Meizhi数据
     * @param page 页数
     * @param loadListener
     */
    void loadMeizhiData(int page, BaseLoadListener<Meizhi> loadListener);
}
