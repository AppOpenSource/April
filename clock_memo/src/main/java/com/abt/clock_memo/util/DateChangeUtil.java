package com.abt.clock_memo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.ContentValues.TAG;

public class DateChangeUtil {

	private static SimpleDateFormat sdf;

	public static String toStringHourMinute(String timeStr) {
		long time = Long.parseLong(timeStr);
		long curTime = System.currentTimeMillis();
		long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天
		long curDay = (long) Math.ceil(curTime/24/60/60/1000.0f);// 天
		android.util.Log.d(TAG, "day: "+day);
		String str = "今天";
		if (curDay-day > 0) {
			str = DateChangeUtil.toStringMinuteDate(time);
		} else {
			sdf = new SimpleDateFormat("HH:mm");
			Date date = new Date(time);
			str = str+" "+sdf.format(date);
		}
		return str;
	}

	public static String toStringDate(long strDate) {
		sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date(strDate);
		String str = sdf.format(date);
		return str;
	}

	public static String toStringHourDate(long strDate) {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH");
		Date date = new Date(strDate);
		String str=sdf.format(date);
		return str;
	}

	public static String toStringHourDates(long strDate) {
		sdf = new SimpleDateFormat("yyyy年MM月dd日 HH");
		Date date = new Date(strDate);
		String str=sdf.format(date);
		return str;
	}

	public static String toStringMinuteDate(long strDate) {
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date(strDate);
		String str=sdf.format(date);
		return str;
	}

	public static Date stringToDate(String strDate) {  
		sdf = new SimpleDateFormat("yyyy年MM月dd日 HH");
		try {
			return sdf.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}  
	} 

	public static String getStandardDate(long strDate) {  
		StringBuffer sb = new StringBuffer();  
		long time = System.currentTimeMillis() - (strDate);  
		long mill = (long) Math.ceil(time /1000);//秒
		long minute = (long) Math.ceil(time/60/1000.0f);// 分钟
		long hour = (long) Math.ceil(time/60/60/1000.0f);// 小时  
		long day = (long) Math.ceil(time/24/60/60/1000.0f);// 天
		if (day-1> 1) { 
			return toStringDate(strDate);
		} else if (hour - 1 > 1) {  
			if (hour >= 24&&hour<48) {  
				sb.append("1天");  
			} else {  
				sb.append(hour + "小时");  
			}  
		} else if (minute - 1 > 0) {  
			if (minute >= 60&&minute <120) {  
				sb.append("1小时");  
			} else {  
				sb.append(minute + "分钟");  
			}  
		} else if (mill - 1 > 0) {  
			if (mill >= 60&&mill<120) {  
				sb.append("1分钟");  
			} else {  
				sb.append(mill + "秒");  
			}  
		} else {  
			sb.append("刚刚");  
		}  
		if (!sb.toString().equals("刚刚")) {  
			sb.append("前");  
		}  
		return sb.toString();  
	}
}
