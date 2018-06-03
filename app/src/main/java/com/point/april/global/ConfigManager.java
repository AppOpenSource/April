package com.point.april.global;

import android.content.Context;

import com.point.april.util.PreferencesUtil;

public class ConfigManager {

	private static final String TAG = ConfigManager.class.getSimpleName();
	private static ConfigManager mConfig;

	public static ConfigManager getInstance() {
		if (mConfig == null) {
			mConfig = new ConfigManager();
		}
		return mConfig;
	}

	public void init(Context context) {
		initGlobalParams();
		
		initLocalCahce(context);
		
		//String host = PreferencesUtil.getString(context, GlobalConstant.KEY_HOST_NAME);
		//if (host == null || "".equalsIgnoreCase(host)) {
		String host = GlobalConstant.HOST_DEFAULT;
		//}
		initCloudURL(context, host);
	}
	
	private static void initGlobalParams() {
		GlobalParams.setDebug(GlobalConstant.DEFAULT_DEBUG_TOGGLE);
		GlobalParams.setAuth(GlobalConstant.DEFAULT_AUTH);
		GlobalParams.setMieToken(GlobalConstant.DEFAULT_TOKEN);
		GlobalParams.setMieUserId(GlobalConstant.DEFAULT_MIE_USER_ID);
		GlobalParams.setDate(GlobalConstant.DEFAULT_MIE_DATE);
		GlobalParams.setUserId(GlobalConstant.DEFAULT_USER_ID);
		GlobalParams.setCommunId(GlobalConstant.DEFAULT_COMMUN_ID);
		GlobalParams.setOriginal(GlobalConstant.DEFAULT_ORIGINAL);
		GlobalParams.setUserName(GlobalConstant.DEFAULT_USER_NAME);
		GlobalParams.setBirthday(GlobalConstant.DEFAULT_BIRTHDAY);
		GlobalParams.setNickname(GlobalConstant.DEFAULT_NICKNAME);
		GlobalParams.setSex(GlobalConstant.DEFAULT_SEX);
		GlobalParams.setSsotoken(GlobalConstant.DEFAULT_SSOTOKEN);
		GlobalParams.setCity_id(GlobalConstant.DEFAULT_CITY_ID);
		GlobalParams.setCity_name(GlobalConstant.DEFAULT_CITY_NAME);
		GlobalParams.setCommunId(GlobalConstant.DEFAULT_COMMUN_ID);
		GlobalParams.setCommunity(GlobalConstant.DEFAULT_COMMUNITY);
		GlobalParams.setRidgepole(GlobalConstant.DEFAULT_RIDGEPOLE);
		GlobalParams.setRidgepole_id((GlobalConstant.DEFAULT_RIDGEPOLE_ID));
		GlobalParams.setNotify_id(GlobalConstant.DEFAULT_NOTIFY_ID);

	}
	
	private static void initLocalCahce(Context context) {
		PreferencesUtil.write(context, GlobalConstant.KEY_COMMUN_ID, GlobalConstant.DEFAULT_COMMUNITY_ID);
	}
	
	public static void initCloudURL(Context context, String host) {
		GlobalConstant.HOST= host;
		PreferencesUtil.write(context, GlobalConstant.KEY_HOST_NAME, GlobalConstant.HOST);
		
		GlobalConstant.URL_SEND_PROPERTY_WARRANTY = GlobalConstant.HOST+"/SCMS/app/service/sendpropertywarranty.do";
		GlobalConstant.URL_GET_LATEST_NEWS = GlobalConstant.HOST+"/SCMS/app/service/getservicelist.do";
		GlobalConstant.URL_GET_MORE_NEWS = GlobalConstant.HOST+"/SCMS/app/service/getlistbypage.do";
		GlobalConstant.URL_SEND_PROBLEM_FEEDBACK = GlobalConstant.HOST+"/SCMS/app/service/sendproblemfeedback.do";
		GlobalConstant.URL_GET_FEEDBACKS = GlobalConstant.HOST+"/SCMS/app/service/getfeedbacks.do";
		GlobalConstant.URL_GET_CIVIL_GUIDE = GlobalConstant.HOST+"/SCMS/app/service/getcivilguide.do";
		GlobalConstant.URL_GET_GUIDE_DETAIL = GlobalConstant.HOST+"/SCMS/app/service/getguidedetail.do";
		GlobalConstant.URL_USER_AUTH = GlobalConstant.HOST+"/SCMS/app/information/user_auth.do";
		GlobalConstant.URL_OWNER_AUTH = GlobalConstant.HOST+"/SCMS/app/information/owner_auth.do";
		GlobalConstant.URL_USER_EDIT = GlobalConstant.HOST+"/SCMS/app/information/user_edit.do";
		GlobalConstant.URL_GET_MORE_INFORM = GlobalConstant.HOST+"/SCMS/app/notification/getNotification.do";
		GlobalConstant.URL_GET_NEW_INFORM = GlobalConstant.HOST+"/SCMS/app/notification/getNewNotification.do";
		GlobalConstant.URL_ADD_SUPPORT = GlobalConstant.HOST+"/SCMS/app/notification/addSupport.do";
		GlobalConstant.URL_IMG_UPLOAD = GlobalConstant.HOST+"/SCMS/app/service/imageUpload.do";
		GlobalConstant.URL_GET_AIR_QUALITY = GlobalConstant.HOST+"/SCMS/app/airquality/show.do";
		GlobalConstant.URL_GET_TOOL_LIST = GlobalConstant.HOST+"/SCMS/app/service/gettoollist.do";
		GlobalConstant.URL_GET_CARPORT = GlobalConstant.HOST+"/SCMS/app/park/askfor.do";
		GlobalConstant.URL_GET_ACCESS_RECORDS = GlobalConstant.HOST+"/SCMS/app/park/record.do";
		GlobalConstant.URL_INVITE_VISITOR = GlobalConstant.HOST+"/SCMS/app/park/visitor.do";
		GlobalConstant.URL_DEL_SERVICE_ITEM = GlobalConstant.HOST+"/SCMS/app/service/deletebyid.do";
		GlobalConstant.URL_GET_CARS = GlobalConstant.HOST+"/SCMS/app/park/getmycar.do";
		GlobalConstant.URL_GET_MSGS = GlobalConstant.HOST+"/SCMS/app/notification/getMyNotification.do";
		GlobalConstant.URL_CAR_CONTROL = GlobalConstant.HOST+"/SCMS/app/park/passport.do";
		GlobalConstant.URL_GET_AUTH_COMMUNITIES = GlobalConstant.HOST+"/SCMS/app/community/getAuthCommunities.do";
		GlobalConstant.URL_GET_CITIES = GlobalConstant.HOST+"/SCMS/app/community/getCitys.do";
		GlobalConstant.URL_GET_COMMUNITIES = GlobalConstant.HOST+"/SCMS/app/community/getCommunitys.do";
		GlobalConstant.URL_GET_BUILDINGS = GlobalConstant.HOST+"/SCMS/app/community/getRidgepoles.do";
		GlobalConstant.URL_GET_HOUSES = GlobalConstant.HOST+"/SCMS/app/community/getHouse.do";
		GlobalConstant.URL_GET_VISITOR_RECORD = GlobalConstant.HOST+"/SCMS/app/park/vrecords.do";
		GlobalConstant.URL_GET_FROM_MIE = GlobalConstant.HOST+"/SCMS/app/information/user_getFromMie.do";
		GlobalConstant.URL_SUGGESTION_INSERT = GlobalConstant.HOST+"/SCMS/app/information/suggestionInsert.do";
		/*
		public static String GET_AIR_QUALITY_DEMO = HOST+"/SCMS/demo/app/airquality/show.do";
		public static String GET_COMMU_INFORM_DEMO = HOST+"/SCMS/demo/app/notification/getAllNotification.do";
		public static String GET_INFORM_DETAIL_DEMO = HOST+"/SCMS/demo/app/notification/getNotificationInfo.do";
		public static String USER_AUTH_DEMO = HOST+"/SCMS/demo/app/information/user_auth.do";
		public static String OWNER_IDENTIFY_DEMO = HOST+"/SCMS/demo/app/information/owner_auth.do";
		public static String GET_CIVIL_GUIDE_DEMO = HOST+"/SCMS/demo/app/service/getcivilguide.do";
		public static String GET_WARRANTIES_DEMO = HOST+"/SCMS/demo/app/service/getservicelist.do";
		public static String SEND_PROPERTY_WARRANTY_DEMO = HOST+"/SCMS/demo/app/service/sendpropertywarranty.do";
		public static String GET_TOOL_LIST_DEMO = HOST+"/SCMS/demo/app/service/gettoollist.do";
		public static String GET_GUIDE_DETAIL_DEMO = HOST+"/SCMS/demo/app/service/getguidedetail.do";
		public static String ADD_SUPPORT_DEMO = HOST+"/SCMS/demo/app/notification/addSupport.do";
		public static String GET_CARPORT_DEMO = HOST+"/SCMS/demo/app/park/askfor.do";
		public static String GET_ACCESS_RECORDS_DEMO = HOST+"/SCMS/demo/app/park/record.do";
		public static String INVITE_VISITOR_DEMO = HOST+"/SCMS/demo/app/park/visitor.do";*/
	}
}
