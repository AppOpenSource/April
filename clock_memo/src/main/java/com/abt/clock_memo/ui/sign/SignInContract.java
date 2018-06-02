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
    public interface IView {

        /**
         * 显示删除对话框
         */
        void showDeleteDialog();

        /**
         * 显示更多对话框
         */
        void showMoreDialog();

        void dismissDialog();

    }

}
