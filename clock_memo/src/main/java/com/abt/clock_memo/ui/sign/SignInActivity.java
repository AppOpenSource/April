package com.abt.clock_memo.ui.sign;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.abt.basic.arch.mvvm.view.BaseActivity;
import com.abt.basic.arch.mvvm.viewmodel.ToolbarViewModel;
import com.abt.clock_memo.bean.SignIn;
import com.abt.clock_memo.data.file.FileHelper;
import com.abt.clock_memo.data.file.FileManager;
import com.abt.clock_memo.global.PreferenceConstant;
import com.abt.clock_memo.test.MockData;
import com.abt.clock_memo.ui.adapter.SignInAdapter;
import com.abt.clock_memo.util.LocationUtil;
import com.abt.clock_memo.util.PreferencesUtil;
import com.j256.ormlite.dao.Dao;

import java.util.List;

/**
 * @描述： @打卡记录页面
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class SignInActivity extends BaseActivity<SignInFragment, SignInViewModel,
        ToolbarViewModel> implements SignInNavigator {

    private String TAG = SignInActivity.class.getSimpleName();
    private List<SignIn> mSignInList;
    private SignInAdapter mAdapter;
    private Dao<SignIn, Integer> stuDao;
    private SignIn mSignIn;

    @NonNull
    @Override
    protected SignInFragment createFragment() {
        return SignInFragment.newInstance();
    }

    @NonNull
    @Override
    protected SignInViewModel createViewModel() {
        return new SignInViewModel();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mSignInList = MockData.getRecordList();
        //mSignInList = FileHelper.getStorageEntities(PreferenceConstant.FILE_NAME_SIGN_IN);
        //mSignInList = FileManager.read(SignInActivity.this, PreferenceConstant.FILE_NAME_SIGN_IN);

        /*if (mSignInList != null) {
            Log.d(TAG, "mSignInList: " + mSignInList.size());
            for (int i = 0; i < mSignInList.size(); i++) {
                Log.d(TAG, "mSignInList(" + i + ")-->time: " + mSignInList.get(i).getTime());
            }
        }
        mAdapter = new SignInAdapter(this, mSignInList);


        mSignInListView.setAdapter(mAdapter);
        mSignInListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SignInActivity.this, "第" + (position + 1) + "条记录", Toast.LENGTH_SHORT).show();
            }
        });*/


        Intent intent = getIntent();
        String res = intent.getExtras().getString(PreferenceConstant.SIGN_IN_OR_NOT);
        if ("yes".equalsIgnoreCase(res)) {
            handleSignIn();
            // 记录打卡次数
            int count = PreferencesUtil.getInt(this, PreferenceConstant.SHARE_KEY_SIGN_IN_COUNT);
            PreferencesUtil.write(this, PreferenceConstant.SHARE_KEY_SIGN_IN_COUNT, ++count);
        } else {
            // 记录浏览打卡记录次数
            int count = PreferencesUtil.getInt(this, PreferenceConstant.SHARE_KEY_SIGN_IN_RECORD_COUNT);
            PreferencesUtil.write(this, PreferenceConstant.SHARE_KEY_SIGN_IN_RECORD_COUNT, ++count);
        }
    }

    private void handleSignIn() {
        Log.d(TAG, "handleSignIn()");
        // 处理前 Loading
        /*mDialog = new ProgressDialog(SignInActivity.this);
        mDialog.setTitle("正在打卡，请稍候...");
        mDialog.setCancelable(true);
        mDialog.show();*/
        Log.d(TAG, "Dialog showing...");

        // 处理中 Signing
        LocationUtil util = LocationUtil.getInstance();
        boolean res = util.signIn(this);
        Log.d(TAG, "signIn res: " + res);

        // 处理后
        SignIn in = new SignIn();
        long time = System.currentTimeMillis();
        String nickName = PreferencesUtil.getString(SignInActivity.this, PreferenceConstant.SHARE_KEY_NICK_NAME);
        int count = PreferencesUtil.getInt(this, PreferenceConstant.SHARE_KEY_SIGN_IN_COUNT) + 1;
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
            FileManager.write(SignInActivity.this, PreferenceConstant.FILE_NAME_SIGN_IN, mSignInList);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationUtil util = LocationUtil.getInstance();
        util.stopLocate();
    }

    public static void startActivity(Context context, String str) {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.putExtra(PreferenceConstant.SIGN_IN_OR_NOT, str);
        context.startActivity(intent);
    }

    @Override
    public void openSignInActivity() {

    }

    @Override
    public void openSignRecordActivity() {

    }

}
