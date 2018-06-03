package com.point.april.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.Field;
import java.util.logging.LogManager;

public class ScreenUtils {
	
	private static final String TAG = "ScreenUtils";
	
	/**
	 * 将px值转换为dip或dp值，保证尺寸大小不变
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip或dp值转换为px值，保证尺寸大小不变
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		// LogManager.v(TAG, "dip2px :" + (int) (dipValue * scale + 0.5f));
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变 n
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 获取设备屏幕密度
	 */
	public static float getDisplayDensity(Context context) {
		float scale = context.getResources().getDisplayMetrics().density;
		// LogManager.v(TAG, "DisplayDensity = " + scale);
		return scale;
	}

	public static float getScaledDensity(Context context) {
		float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		// LogManager.v(TAG, "ScaledDensity = " + fontScale);
		return fontScale;
	}

	public static float getDensityDpi(Context context) {
		float densityDpi = context.getResources().getDisplayMetrics().densityDpi;
		// LogManager.v(TAG, "DensityDpi = " + densityDpi);
		return densityDpi;
	}

	public static int getScreenWidth(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int screenWidth = dm.widthPixels;
		// LogManager.v(TAG, "screenWidth :" + screenWidth);
		return screenWidth;
	}

	public static int getScreenHeight(Context context) {
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		int screenHeight = dm.heightPixels;
		// LogManager.v(TAG, ",screenHeight: " + screenHeight);

		return screenHeight;
	}

	public static int getStatusBarHeight(Activity activity) {
		Rect outRect = new Rect();
		activity.getWindow().getDecorView()
		.getWindowVisibleDisplayFrame(outRect);
		// LogManager.v(TAG, "top:" + outRect.top + " ; left: " + outRect.left);
		return outRect.top;
	}

	public static void printSreenInfo(Context ctx) {

	}

	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;

		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = context.getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// LogManager.v(TAG, ",status bar heigth: " + sbar);
		return sbar;
	}

	public static int getAppAreaHeight(Context context) {
		return getScreenHeight(context) - getStatusBarHeight(context);
	}

	public static int getAppAreaWidth(Context context) {
		return getScreenWidth(context);
	}

	public static void setMarginLeft(View view, int x) {
		MarginLayoutParams margin = new MarginLayoutParams(
				view.getLayoutParams());
		margin.setMargins(x, 0, 0, 0);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				margin);
		view.setLayoutParams(layoutParams);
	}

	public static void setMarginRight(View view, int x) {
		MarginLayoutParams margin = new MarginLayoutParams(
				view.getLayoutParams());
		margin.setMargins(0, 0, x, 0);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				margin);
		view.setLayoutParams(layoutParams);
	}

	public static void setMarginTop(View view, int y) {
		MarginLayoutParams margin = new MarginLayoutParams(
				view.getLayoutParams());
		margin.setMargins(0, y, 0, 0);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				margin);
		view.setLayoutParams(layoutParams);
	}

	public static void setMarginBottom(View view, int y) {
		MarginLayoutParams margin = new MarginLayoutParams(
				view.getLayoutParams());
		margin.setMargins(0, 0, 0, y);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				margin);
		view.setLayoutParams(layoutParams);
	}

	public static void setLayoutByXY(View view, int x, int y) {
		MarginLayoutParams margin = new MarginLayoutParams(
				view.getLayoutParams());
		margin.setMargins(x, y, 0, 0);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
				margin);

		view.setLayoutParams(layoutParams);
	}

	public static void setViewParentAlign(Context context, View view, int verb) {
		RelativeLayout.LayoutParams lpp = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lpp.addRule(verb, RelativeLayout.TRUE);
		lpp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
		lpp.setMargins(0, ScreenUtils.dip2px(context, 20), 0, 0);
		view.setLayoutParams(lpp);
	}

	public static int getHeight(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		// LogManager.v(TAG, "getHeight = " + view.getMeasuredHeight());
		return view.getMeasuredHeight();
	}

	public static int getWidht(View view) {
		int w = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		int h = View.MeasureSpec.makeMeasureSpec(0,
				View.MeasureSpec.UNSPECIFIED);
		view.measure(w, h);
		// LogManager.v(TAG, "getWidht = " + view.getMeasuredWidth());
		return view.getMeasuredWidth();
	}

	public static void setComponentWH(View view, float w, float h,
			int screenWidth, int screenHight) {
		view.setLayoutParams(new LinearLayout.LayoutParams((int) (screenWidth
				* w + 0.5f), (int) (screenHight * h + 0.5f)));
	}
}
