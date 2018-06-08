package com.abt.price.base;

/**
 * @描述： @IBaseView
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public interface IBaseView {
    /**
     * 开始加载
     * @param loadType 加载的类型 0：第一次记载 1：下拉刷新 2：上拉加载更多
     */
    void loadStart(int loadType);

    /**
     * 加载完成
     */
    void loadComplete();

    /**
     * 加载失败
     * @param message
     */
    void loadFailure(String message);
}
