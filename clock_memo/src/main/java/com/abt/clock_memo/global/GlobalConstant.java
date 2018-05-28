package com.abt.clock_memo.global;

import android.os.Environment;

public class GlobalConstant {
	// -------------------------GLOBAL------------------------------------
	public static final String PAK_NAME = "com.point.april";
	public static final String SD_PATH = Environment.getExternalStorageDirectory()+"";
	public static final String FORMATS_PATH = SD_PATH+"/formats/";
	public static final String SHARED_TABLE = "april_shared_table";
	public static final String LOG_PATH = SD_PATH+"/SmartCommunity/log";
	public static final String QIUT_APPLICATION_ACTION = PAK_NAME+".action_quit_app";//退出应用的广播
	// -------------------------GLOBAL------------------------------------

	// ------------------------ APRIL ----------------
	public static final String LAST_LOCATION_TIME = "LAST_LOCATION_TIME";
	public static final String LOCATION = "location";
	public static final String LOCATION_ACTION = "locationAction";
	public static final String KEY_ID = "id";
	public static final String KEY_TYPE = "type";
	// ------------------------ APRIL ----------------


	// -------------------------ERROR CODE------------------------------------
	public static final int FAILED = 0;
	public static final int SEND_FAILED = -1;
	public static final int REQUEST_FAILED = -2;
	public static final int PARSE_FAILED = -3;
	public static final int EROOR_CODE = FAILED;
	// -------------------------ERROR CODE------------------------------------


	// -------------------------SHARE-PREFERENCE-KEY------------------------------------
	public static final String SHARE_KEY_USER_NAME = "phoneNum";
	public static final String SHARE_KEY_NICK_NAME = "nickName";
	public static final String SHARE_KEY_IS_LOGIN = "isLogin";
	public static final String SHARE_KEY_ADDRESS = "address";
	public static final String SHARE_KEY_SSO_TOKEN = "ssoToken";
	public static final String SHARE_KEY_TOKEN = "token";
	public static final String SHARE_KEY_HEAD_PIC = "headPic";
	public static final String SHARE_KEY_PHONE = "phone";
	public static final String SHARE_KEY_WARRANTY_ID = "warranty_id";
	public static final String SHARE_KEY_PROBLEM_ID = "problem_id";
	public static final String SHARE_KEY_USER_ID = "phone";
	public static final String SHARE_KEY_SEX = "sex";
	public static final String SHARE_KEY_BIRTHDAY = "birthday";
	public static final String SHARE_KEY_APP_OPEN_COUNT = "SHARE_KEY_APP_OPEN_COUNT";
	public static final String SHARE_KEY_SIGN_IN_COUNT = "SHARE_KEY_SIGN_IN_COUNT";
	public static final String SHARE_KEY_SIGN_IN_RECORD_COUNT = "SHARE_KEY_SIGN_IN_RECORD_COUNT";
	public static final String SHARE_KEY_OPEN_COACH_COUNT = "SHARE_KEY_OPEN_COACH_COUNT";
	public static final String SHARE_KEY_OPEN_NEWS_COUNT = "SHARE_KEY_OPEN_NEWS_COUNT";
	public static final String SHARE_KEY_NOTIFICATION_COUNT = "SHARE_KEY_NOTIFICATION_COUNT";
	// -------------------------SHARE-PREFERENCE-KEY------------------------------------

	// -------------------------SHARE-PREFERENCE-VALUE------------------------------------
	public static final String SHARE_VALUE_USER_NAME = "username";
	public static final String SHARE_VALUE_NICK_NAME = "nickname";
	public static final String SHARE_VALUE_IS_LOGIN = "yes";
	public static final String SHARE_VALUE_IS_NOT_LOGIN = "no";
	// -------------------------SHARE-PREFERENCE-VALUE------------------------------------

	public static final String FILE_NAME_SIGN_IN = "sign";
	public static final String SIGN_IN_OR_NOT = "sign_in_or_not";

	// -------------------------KEY------------------------------------
	public static final String KEY_AUTH = "auth";
	public static final String KEY_MIE_TOKEN = "mie-token";
	public static final String KEY_MIE_USER_ID = "mie-userid";
	public static final String KEY_X_SCMS_DATE = "x-scms-date";
	public static final String KEY_USER_ID = "user_id";
	public static final String KEY_COMMUN_ID = "commun_id";
	public static final String KEY_NEWS_ID = "news_id";
	// -------------------------KEY------------------------------------

	// -------------------------VALUE------------------------------------
	public static final boolean DEFAULT_DEBUG_TOGGLE = false;
	public static final String DEFAULT_AUTH = "24Fat4dw74jWiOPyOted1Pf9=";
	public static final String DEFAULT_TOKEN = "24Fat4dw74jWiOP";
	public static final String DEFAULT_MIE_USER_ID = "1";
	public static final String DEFAULT_MIE_DATE = "1463106697278";
	public static final String DEFAULT_USER_ID = "1";
	public static final int DEFAULT_COMMUN_ID = 1;
	public static final String DEFAULT_ORIGINAL = "MIE";
	public static final String DEFAULT_USER_NAME = "Warren";
	public static final String DEFAULT_BIRTHDAY = "1996";;
	public static final String DEFAULT_NICKNAME = "小李子";
	public static final String DEFAULT_SEX = "Man";
	public static final String DEFAULT_SSOTOKEN = "24Fat4dw74jWiOP";
	public static final String DEFAULT_INFORM_ID = "1";
	public static final String DEFAULT_CITY_ID = "6";
	public static final String DEFAULT_CITY_NAME = "深圳市";
	public static final String DEFAULT_COMMUNITY_ID = "1";
	public static final String DEFAULT_COMMUNITY = "丽晶国际";
	public static final String DEFAULT_RIDGEPOLE = "一期一栋";
	public static final String DEFAULT_RIDGEPOLE_ID = "1";
	public static final int DEFAULT_NOTIFY_ID = 1;
	// -------------------------VALUE------------------------------------

	// public static final String SERVER_IP = "192.168.100.190";// 本地IP
	// public static final String SERVER_IP = "192.168.31.146";// 西丽本地IP
	public static final String SERVER_IP = "192.168.1.104";// 本地IP

	// ------------------------ DB ----------------------------
	public static final String DB_NAME = "cookbook";
	public static final String USER = "root";
	public static final String PASSWD = "123456";
	public static final String DB_URL = "jdbc:mysql://" + SERVER_IP + ":33306/" + DB_NAME;
	// ------------------------ DB ----------------------------

	// ------------------------ HTTP ----------------------------
	//public static final String HOST_DEFAULT = "http://61.49.178.34:28080";// 北京IP
	public static final String HOST_DEFAULT = "http://"+SERVER_IP+":8080";
	public static String HOST = HOST_DEFAULT;
	public static String URL_SEND_PROPERTY_WARRANTY;
	public static String URL_GET_LATEST_NEWS;
	public static String URL_GET_MORE_NEWS;
	public static String URL_SEND_PROBLEM_FEEDBACK;
	public static String URL_GET_FEEDBACKS;
	public static String URL_GET_CIVIL_GUIDE;
	public static String URL_GET_GUIDE_DETAIL;
	public static String URL_USER_AUTH;
	public static String URL_OWNER_AUTH;
	public static String URL_USER_EDIT;
	public static String URL_GET_MORE_INFORM;
	public static String URL_GET_NEW_INFORM;
	public static String URL_ADD_SUPPORT;
	public static String URL_IMG_UPLOAD;
	public static String URL_GET_AIR_QUALITY;
	public static String URL_GET_TOOL_LIST;
	public static String URL_GET_CARPORT;
	public static String URL_GET_ACCESS_RECORDS;
	public static String URL_INVITE_VISITOR;
	public static String URL_DEL_SERVICE_ITEM;
	public static String URL_GET_CARS;
	public static String URL_GET_MSGS;
	public static String URL_CAR_CONTROL;
	public static String URL_GET_AUTH_COMMUNITIES;
	public static String URL_GET_CITIES;
	public static String URL_GET_COMMUNITIES;
	public static String URL_GET_BUILDINGS;
	public static String URL_GET_HOUSES;
	public static String URL_GET_VISITOR_RECORD;
	public static String URL_GET_FROM_MIE;
	public static String URL_SUGGESTION_INSERT;
	// ------------------------ HTTP ----------------------------

	// -------------------------RESPONE TAG------------------------------------
	public static final String RES_TAG_RES = "success_ornot";
	public static final String RES_TAG_OK = "OK";
	public static final String RES_TAG_NG = "NG";
	public static final String RES_TAG_MSG = "message";
	// -------------------------RESPONE TAG------------------------------------

	public static final String KEY_HOST_NAME = "host";
	public static final String KEY_NOTIFY_ID = "notification_id";
	public static final String REQ_TAG_GET_NEW_INFORM = "GET_NEW_INFORM_TAG";

	public static final String TX_APP_KEY = "1ae28fc9dd5afadc696ad94cd59426d8";
	public static final String WEIXIN = "weixin";
	public static final String DB__IS_READ_NAME = "IsRead";
}
