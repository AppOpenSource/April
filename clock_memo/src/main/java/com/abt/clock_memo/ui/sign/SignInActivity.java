package com.abt.clock_memo.ui.sign;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

import com.abt.basic.arch.mvvm.view.BaseActivity;
import com.abt.basic.arch.mvvm.viewmodel.ToolbarViewModel;
import com.abt.clock_memo.global.PreferenceConstant;
import com.abt.clock_memo.util.LocationUtil;

/**
 * @描述： @打卡记录页面
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class SignInActivity extends BaseActivity<SignInFragment, SignInViewModel,
        ToolbarViewModel> implements SignInNavigator {

    @NonNull
    @Override
    protected SignInFragment createFragment() {
        return SignInFragment.newInstance();
    }

    @NonNull
    @Override
    protected SignInViewModel createViewModel() {
        return new SignInViewModel(getIntent());
    }

    public static void startActivity(Context context, String str) {
        Intent intent = new Intent(context, SignInActivity.class);
        intent.putExtra(PreferenceConstant.SIGN_IN_OR_NOT, str);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocationUtil util = LocationUtil.getInstance();
        util.stopLocate();
    }

}
