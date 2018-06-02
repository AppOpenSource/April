package com.abt.clock_memo.ui.main;

import com.abt.basic.arch.mvvm.view.INavigator;

/**
 * @描述： @MainNavigator
 * @作者： @黄卫旗
 * @创建时间： @02/06/2018
 */
public interface MainNavigator extends INavigator {

    void openSignInActivity();
    void openSignRecordActivity();
}
