package com.abt.clock_memo.test;

import android.util.Log;

import com.abt.clock_memo.bean.SignIn;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * @描述： @
 * @作者： @黄卫旗
 * @创建时间： @2016/11/2
 */
public class MockData {

    /*public static List<Coach> getCoachList() {
        ArrayList coachList = new ArrayList<Coach>();
        Coach ye = new Coach();
        ye.setName("叶洪泽");
        ye.setSex("男");
        ye.setStar("☆☆☆☆☆☆");
        ye.setWhich_school("广仁驾校");

        Coach in = new Coach();
        in.setName("方中信");
        in.setSex("男");
        in.setStar("☆☆☆☆☆☆☆☆");
        in.setWhich_school("远大驾校");

        Coach chang = new Coach();
        chang.setName("常遇春");
        chang.setSex("男");
        chang.setStar("☆☆☆☆☆☆☆☆☆");
        chang.setWhich_school("福田驾校");

        coachList.add(ye);
        coachList.add(in);
        coachList.add(chang);
        return coachList;
    }*/

    public static List<SignIn> getRecordList() {
        ArrayList list = new ArrayList<SignIn>();
        long time = System.currentTimeMillis();
        Log.d(TAG, "time: "+time);
        for (int i=0; i<10; i++) {
            SignIn in = new SignIn();
            in.setAddress("place:"+i);
            in.setName("梓欣");
            in.setTime("147800"+i+"699769");
            in.setStatus("刷卡成功");
            list.add(in);
        }
        return list;
    }
}
