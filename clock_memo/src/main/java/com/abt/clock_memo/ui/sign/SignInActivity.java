package com.abt.clock_memo.ui.sign;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.abt.basic.arch.mvvm.view.BaseActivity;
import com.abt.basic.arch.mvvm.viewmodel.ToolbarViewModel;
import com.abt.clock_memo.bean.SignIn;
import com.abt.clock_memo.global.PreferenceConstant;
import com.abt.clock_memo.ui.adapter.SignInListAdapter;
import com.abt.clock_memo.util.LocationUtil;
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
    private SignInListAdapter mAdapter;
    private List<SignIn> mSignInList;
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
        /*mSignInList = getRecordList();
        mSignInList = FileHelper.getStorageEntities(GlobalConstant.FILE_NAME_SIGN_IN);
        mSignInList = FileManager.read(SignInActivity.this, PreferenceConstant.FILE_NAME_SIGN_IN);
        if (mSignInList != null) {
            Log.d(TAG, "mSignInList: " + mSignInList.size());
            for (int i = 0; i < mSignInList.size(); i++) {
                Log.d(TAG, "mSignInList(" + i + ")-->time: " + mSignInList.get(i).getTime());
            }
        }
        mAdapter = new SignInListAdapter(this, mSignInList);
        mSignInListView.setAdapter(mAdapter);
        mSignInListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SignInActivity.this, "第" + (position + 1) + "条记录", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = getIntent();
        String res = intent.getExtras().getString(PreferenceConstant.SIGN_IN_OR_NOT);
        if ("yes".equalsIgnoreCase(res)) {
            //handleSignIn(); TODO 要打开
            // 记录打卡次数
            int count = PreferencesUtil.getInt(this, PreferenceConstant.SHARE_KEY_SIGN_IN_COUNT);
            PreferencesUtil.write(this, PreferenceConstant.SHARE_KEY_SIGN_IN_COUNT, ++count);
        } else {
            // 记录浏览打卡记录次数
            int count = PreferencesUtil.getInt(this, PreferenceConstant.SHARE_KEY_SIGN_IN_RECORD_COUNT);
            PreferencesUtil.write(this, PreferenceConstant.SHARE_KEY_SIGN_IN_RECORD_COUNT, ++count);
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
