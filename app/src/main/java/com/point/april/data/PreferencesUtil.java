package com.point.april.data;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.point.april.global.GlobalConstant;

public class PreferencesUtil {

	public static void write(Context context, String key, String value) {
		if (context == null) return;
		SharedPreferences prefs = context.getSharedPreferences(GlobalConstant.SHARED_TABLE, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putString(key, value);
		editor.commit();
	}
	
	public static void write(Context context, String key, int value) {
		SharedPreferences prefs = context.getSharedPreferences(GlobalConstant.SHARED_TABLE, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putInt(key, value);
		editor.commit();
	}
	
	public static void write(Context context, String key, Long value) {
		SharedPreferences prefs = context.getSharedPreferences(GlobalConstant.SHARED_TABLE, Activity.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();
		editor.putLong(key, value);
		editor.commit();
	}

	public static String getString(Context context, String key) {
		SharedPreferences prefs = context.getSharedPreferences(GlobalConstant.SHARED_TABLE, Activity.MODE_PRIVATE);
		return prefs.getString(key, "");
	}

	public static int getInt(Context context, String key) {
		SharedPreferences prefs = context.getSharedPreferences(GlobalConstant.SHARED_TABLE, Activity.MODE_PRIVATE);
		return prefs.getInt(key, 0);
	}
	
	public static long getLong(Context context, String key) {
		SharedPreferences prefs = context.getSharedPreferences(GlobalConstant.SHARED_TABLE, Activity.MODE_PRIVATE);
		return prefs.getLong(key, 0L);
	}
}
