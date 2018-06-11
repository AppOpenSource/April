package com.abt.price.util;

import java.util.Calendar;
import java.util.Date;

/**
 * @描述： @DateUtils
 * @作者： @黄卫旗
 * @创建时间： @11/06/2018
 */
public class DateUtils {

    public static boolean isSameDate(Date date1, Date date2) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.setTime(date2);

        return cal.get(Calendar.DAY_OF_YEAR) == selectedDate.get(Calendar.DAY_OF_YEAR);
    }

}
