package com.point.april.widget;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

public class CustomDialogHelper {

	private static Dialog mDialog = null;
	private static Dialog mToastDialog = null;

	private static Handler mHandler = new Handler(){
		@SuppressLint("NewApi")
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case 1:
				if (mToastDialog != null && mToastDialog.isShowing()) {
					mToastDialog.dismiss();
					mToastDialog = null;
				}
				break;
			case 2:
				if (mDialog != null && mDialog.isShowing()) {
					mDialog.dismiss();
					mDialog = null;
					if(onCancelLisener!=null){
						onCancelLisener.onCancelRequest();
					}
				}
				break;
			
			default:
				break;
			}
		}
	};

	public static void dismiss() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
			mDialog = null;
		}
	}
	public static void dismissToast() {
		if (mToastDialog != null && mToastDialog.isShowing()) {
			mToastDialog.dismiss();
			mToastDialog = null;
		}
	}

	public static Dialog createToastDialog(Context context,String msg,int timeout,boolean type){
		ToastDialog.Builder builder = new ToastDialog.Builder(context);
		builder.setMessage(msg);
		builder.setImageView(type);
		if(mToastDialog==null){
			mToastDialog = builder.create();
			mToastDialog.setCanceledOnTouchOutside(false);
			mToastDialog.setCancelable(true);
			mHandler.sendEmptyMessageDelayed(1, timeout);
		}
		return mToastDialog;
	}

	public static Dialog createPrograssDialog(Context context,String msg,int timeout){
		PrograssDialog.Builder builder = new PrograssDialog.Builder(context);
		builder.setMessage(msg);
		if(mDialog==null){
			mDialog = builder.create();
			mDialog.setCanceledOnTouchOutside(false);
			mDialog.setCancelable(true);
			mHandler.sendEmptyMessageDelayed(2, timeout);
		}
		return mDialog;
	}

	private static onCancelRequeLisener onCancelLisener;

	public interface onCancelRequeLisener {
		public void onCancelRequest();
	}

	public static void setOnCancelRequestLisener(onCancelRequeLisener Lisener) {
		onCancelLisener = Lisener;
	}
}
