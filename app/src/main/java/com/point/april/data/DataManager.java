package com.point.april.data;

import android.util.Log;

import com.point.april.data.file.FileHelper;
import com.point.april.data.preference.PreferenceHelper;

public class DataManager {

	private static final String TAG = DataManager.class.getSimpleName();
	private DataManager mManager;
	
	private FileHelper mFileHelper;
	private PreferenceHelper mPreHelper;
	
	public DataManager getInstance() {
		if (mManager == null) {
			mManager = new DataManager();
		}
		return mManager;
	}
	
	public void init() {
		mFileHelper = new FileHelper();
		mPreHelper = new PreferenceHelper();
	}
	
	public void test() {
		Log.d(TAG, mFileHelper.toString());
		Log.d(TAG, mPreHelper.toString());
	}
}
