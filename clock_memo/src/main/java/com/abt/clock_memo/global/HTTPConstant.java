package com.abt.clock_memo.global;

/**
 * @描述： @HTTPConstant
 * @作者： @黄卫旗
 * @创建时间： @02/06/2018
 */
public class HTTPConstant {

    // ------------------------ HTTP ----------------------------
    //public static final String HOST_DEFAULT = "http://61.49.178.34:28080";// 北京IP
    public static final String HOST_DEFAULT = "http://"+GlobalConstant.SERVER_IP+":8080";
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

}
