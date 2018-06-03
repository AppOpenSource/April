package com.abt.clock_memo.model;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.clock_memo.bean.SignIn;
import com.abt.clock_memo.ui.sign.listener.SignInListener;

/**
 * @描述： @ISignInModel
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public interface ISignInModel {
    /**
     * 获取打卡数据
     * @param page 页数
     * @param loadListener
     */
    void loadClockData(int page, BaseLoadListener<SignIn> loadListener);

    void sign(String type, SignInListener<SignIn> loadListener);
}
