package com.abt.clock_memo.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.Serializable;

/**
 * 界面调用：<br>
 * Preferences preferences = Preferences.Factory.getmInstance(this); <br>
 * String accountId = preferences.getAccountId();
 */
public interface Preferences {

	SharedPreferences getSharedPreferences();

	/**
	 * 获取保存的数据
	 */
	<T> T get(Context context, String key, Class<T> c);

	/**
	 * 保存数据，持久化
	 */
	boolean save(Context context, String key, Serializable obj);

	void setAccountId(String accountId);

	String getAccountId();

	String getToken();

	void setToken(String token);

	static final class Factory {
		public static final Preferences getInstance(Context context) {
			return MemoryPreferences.getInstance(context);
		}
	}
}
