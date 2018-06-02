package com.abt.clock_memo.global;

import android.os.Environment;

public class GlobalConstant {

	public static final String PAK_NAME = "com.abt.clock_memo";
	public static final String SD_PATH = Environment.getExternalStorageDirectory()+"";
	public static final String FORMATS_PATH = SD_PATH+"/formats/";
	public static final String SHARED_TABLE = "april_shared_table";
	public static final String LOG_PATH = SD_PATH+"/SmartCommunity/log";
	public static final String QIUT_APPLICATION_ACTION = PAK_NAME+".action_quit_app";//退出应用的广播

	// public static final String SERVER_IP = "192.168.100.190";// 本地IP
	// public static final String SERVER_IP = "192.168.31.146";// 西丽本地IP
	public static final String SERVER_IP = "192.168.1.104";// 本地IP

	public static final String KEY_HOST_NAME = "host";
	public static final String KEY_NOTIFY_ID = "notification_id";
	public static final String REQ_TAG_GET_NEW_INFORM = "GET_NEW_INFORM_TAG";

	public static final String TX_APP_KEY = "1ae28fc9dd5afadc696ad94cd59426d8";
	public static final String WEIXIN = "weixin";
	public static final String DB__IS_READ_NAME = "IsRead";
}
