package com.abt.clock_memo.data;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatUtil {

	public static boolean isCarnumberNO(String carnumber) {
		/*
	    车牌号格式：汉字 + A-Z + 位A-Z或-
	    （只包括了普通车牌号，教练车和部分部队车等车牌号不包括在内）
		 */
		String carnumRegex = "[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}";
		if (TextUtils.isEmpty(carnumber)) return false;
		else return carnumber.matches(carnumRegex);
	}
	
	public static boolean isMobileNO(String mobiles) {

		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

		Matcher m = p.matcher(mobiles);
		if (TextUtils.isEmpty(mobiles)) return false;
		else return m.matches();

	}
	
	// 判断电话
	public static boolean isTelephone(String phonenumber) {

		String phone = "0\\d{2,3}-\\d{7,8}";

		Pattern p = Pattern.compile(phone);

		Matcher m = p.matcher(phonenumber);
		if (TextUtils.isEmpty(phonenumber)) return false;
		else return m.matches();

	}
}
