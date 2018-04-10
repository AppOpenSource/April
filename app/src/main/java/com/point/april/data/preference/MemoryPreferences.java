package com.point.april.data.preference;

import android.content.Context;

/**
 * 内存存储，如果只需存内存，直接重写具体方法即可
 */
public class MemoryPreferences extends FilePreferences {

	private static Preferences mPre;

	protected MemoryPreferences(Context context) {
		super(context);		
	}

	public static synchronized final Preferences getInstance(Context context) {
		if (mPre == null) {
			mPre = new MemoryPreferences(context.getApplicationContext());
		}
		return mPre;
	}

}
