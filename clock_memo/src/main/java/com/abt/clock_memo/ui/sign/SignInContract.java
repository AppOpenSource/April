package com.abt.clock_memo.ui.sign;

/**
 * @描述： @SignInContract
 * @作者： @黄卫旗
 * @创建时间： @03/06/2018
 */
public interface SignInContract {

    /**
     * 视图接口
     */
    interface IView {

        void loadStart(int var1);

        void loadComplete();

        void loadFailure(String var1);

        void showLoadingDialog();

        void showMoreDialog();

        void dismissDialog();

    }

}
