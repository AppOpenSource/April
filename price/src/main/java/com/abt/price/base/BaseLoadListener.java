package com.abt.price.base;

import java.util.List;

/**
 * @描述： @BaseLoadListener
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public interface BaseLoadListener<T> {
    /**
     * 加载数据成功
     * @param list
     */
    void loadSuccess(List<T> list);

    /**
     * 加载失败
     * @param message
     */
    void loadFailure(String message);

    /**
     * 开始加载
     */
    void loadStart();

    /**
     * 加载结束
     */
    void loadComplete();
}
