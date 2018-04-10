package com.point.base.control;

import com.point.base.BaseApp;

public class Toast {

    private static android.widget.Toast mToast;

    public static void show(String msg) {
        int duration = android.widget.Toast.LENGTH_SHORT;
        if (msg.length() > 10) {
            duration = android.widget.Toast.LENGTH_LONG;
        }
        mToast = android.widget.Toast.makeText(BaseApp.getContext(), msg, duration);
        mToast.show();
    }

    public static void show(int msg) {
        int duration = android.widget.Toast.LENGTH_SHORT;
        mToast = android.widget.Toast.makeText(BaseApp.getContext(), msg, duration);
        mToast.show();
    }
}
