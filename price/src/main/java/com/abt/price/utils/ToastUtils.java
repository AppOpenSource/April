package com.abt.price.utils;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * @描述： @ToastUtils
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class ToastUtils {

    private static Toast toast;

    /**
     * show toast
     * @param context context
     * @param msg     message string
     */
    public static void show(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * show toast
     * @param context context
     * @param msgId   message resource id
     */
    public static void show(Context context, int msgId) {
        if (toast == null) {
            toast = Toast.makeText(context.getApplicationContext(), "", Toast.LENGTH_SHORT);
        }
        toast.setText(msgId);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
