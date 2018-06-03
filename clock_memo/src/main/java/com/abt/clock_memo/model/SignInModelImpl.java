package com.abt.clock_memo.model;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.clock_memo.bean.SignIn;
import com.abt.clock_memo.data.file.FileHelper;
import com.abt.clock_memo.data.file.FileManager;
import com.abt.clock_memo.global.PreferenceConstant;
import com.abt.clock_memo.global.SignInConstant;
import com.abt.clock_memo.ui.sign.listener.SignInListener;
import com.abt.common.app.BasicApplication;
import com.abt.common.util.LocationUtil;
import com.abt.common.util.PreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @描述： @SignInModelImpl
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class SignInModelImpl implements ISignInModel {

    private static final String TAG = "SignInModelImpl";
    private static List<SignIn> mSignInList = new ArrayList<>();

    @Override
    public void loadClockData(final int page, final BaseLoadListener<SignIn> loadListener) {
        loadListener.loadStart();
        /**上拉加载的数据*/
        if (page>1) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadListener.loadSuccess(new ArrayList<SignIn>());
                    loadListener.loadComplete();
                }
            }, 1*1000);
            return;
        }

        /**下拉或者第一次加载数据*/
        mSignInList = FileHelper.getStorageEntities(PreferenceConstant.FILE_NAME_SIGN_IN);
        mSignInList = FileManager.read(BasicApplication.getAppContext(), PreferenceConstant.FILE_NAME_SIGN_IN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadListener.loadSuccess(mSignInList);
                loadListener.loadComplete();
            }
        }, 300);
    }

    /**
     * 打卡
     * @param type "yes" 打卡，"no"什么都不用做
     */
    @Override
    public void sign(String type, final SignInListener<SignIn> signInListener) {
        if (!TextUtils.isEmpty(type) && type.equalsIgnoreCase(SignInConstant.NO)) {
            // 记录浏览打卡记录次数
            int count = PreferencesUtil.getInt(BasicApplication.getAppContext(), PreferenceConstant.SHARE_KEY_SIGN_IN_RECORD_COUNT);
            PreferencesUtil.write(BasicApplication.getAppContext(), PreferenceConstant.SHARE_KEY_SIGN_IN_RECORD_COUNT, ++count);
            return;
        }

        signInListener.signInStart();

        // 处理中 Signing
        LocationUtil util = LocationUtil.getInstance();
        boolean res = util.signIn(BasicApplication.getAppContext());
        Log.d(TAG, "signIn res: " + res);

        // 处理后
        SignIn in = new SignIn();
        long time = System.currentTimeMillis();
        String nickName = PreferencesUtil.getString(BasicApplication.getAppContext(), PreferenceConstant.SHARE_KEY_NICK_NAME);
        int count = PreferencesUtil.getInt(BasicApplication.getAppContext(), PreferenceConstant.SHARE_KEY_SIGN_IN_COUNT) + 1;
        if (TextUtils.isEmpty(nickName)) {
            nickName = "第" + count + "条记录";
        }
        // 记录打卡次数
        PreferencesUtil.write(BasicApplication.getAppContext(), PreferenceConstant.SHARE_KEY_SIGN_IN_COUNT, count);
        in.setName(nickName);
        in.setTime(time + "");
        if (res) { // 打卡成功
            in.setStatus("刷卡成功");
        } else {
            in.setStatus("刷卡失败");
        }
        if (mSignInList != null) {
            mSignInList.add(0, in);
            FileManager.write(BasicApplication.getAppContext(), PreferenceConstant.FILE_NAME_SIGN_IN, mSignInList);
            FileHelper.saveStorage2SDCard(mSignInList, PreferenceConstant.FILE_NAME_SIGN_IN);
            Log.d(TAG, "mAdapter.notifyDataSetChanged(): " + mSignInList.size());

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    signInListener.signInSuccess(mSignInList);
                    signInListener.signInComplete();
                }
            }, 1*1000);
        }
    }

    public static void signIn() {
        Log.d(TAG, "signIn()");
        //mSignInList = MockData.getRecordList();
        mSignInList = FileHelper.getStorageEntities(PreferenceConstant.FILE_NAME_SIGN_IN);
        mSignInList = FileManager.read(BasicApplication.getAppContext(), PreferenceConstant.FILE_NAME_SIGN_IN);

        // 处理前 Loading
        /*mDialog = new ProgressDialog(SignInActivity.this);
        mDialog.setTitle("正在打卡，请稍候...");
        mDialog.setCancelable(true);
        mDialog.show();*/
        Log.d(TAG, "Dialog showing...");

        // 处理中 Signing
        LocationUtil util = LocationUtil.getInstance();
        boolean res = util.signIn(BasicApplication.getAppContext());
        Log.d(TAG, "signIn res: " + res);

        // 处理后
        SignIn in = new SignIn();
        long time = System.currentTimeMillis();
        String nickName = PreferencesUtil.getString(BasicApplication.getAppContext(), PreferenceConstant.SHARE_KEY_NICK_NAME);
        int count = PreferencesUtil.getInt(BasicApplication.getAppContext(), PreferenceConstant.SHARE_KEY_SIGN_IN_COUNT) + 1;
        if (TextUtils.isEmpty(nickName)) {
            nickName = "第" + count + "条记录";
        }
        in.setName(nickName);
        in.setTime(time + "");
        if (res) { // 打卡成功
            // TODO 更新打卡记录
            in.setStatus("刷卡成功");
        } else {
            in.setStatus("刷卡失败");
            //mSignInBtn.setVisibility(View.VISIBLE);
        }
        if (mSignInList != null) {
            mSignInList.add(0, in);
            //mAdapter.notifyDataSetChanged();
            FileManager.write(BasicApplication.getAppContext(), PreferenceConstant.FILE_NAME_SIGN_IN, mSignInList);
            FileHelper.saveStorage2SDCard(mSignInList, PreferenceConstant.FILE_NAME_SIGN_IN);
            Log.d(TAG, "mAdapter.notifyDataSetChanged(): " + mSignInList.size());
        }
        /*if (mDialog != null) {
            mSignInBtn.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mDialog.dismiss();
                }
            }, 1 * 1000);
        }*/

    }
}
