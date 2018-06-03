package com.abt.clock_memo.ui.listener;

import java.util.List;

/**
 * @描述： @SignInListener
 * @作者： @黄卫旗
 * @创建时间： @03/06/2018
 */
public interface SignInListener<T> {
    void signInSuccess(List<T> var1);

    void signInFailure(String var1);

    void signInStart();

    void signInComplete();
}