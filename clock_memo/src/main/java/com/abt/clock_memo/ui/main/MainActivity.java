package com.abt.clock_memo.ui.main;

import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.widget.Toast;

import com.abt.basic.arch.mvvm.view.BaseActivity;
import com.abt.basic.arch.mvvm.viewmodel.ToolbarViewModel;
import com.abt.clock_memo.R;
import com.abt.clock_memo.ui.sign.SignInActivity;
import com.abt.clock_memo.global.SignInConstant;

/**
 * @描述： @ClockMemo首页
 * @作者： @黄卫旗
 * @创建时间： @28/05/2018
 */
public class MainActivity extends BaseActivity<MainFragment, MainViewModel,
        ToolbarViewModel> implements MainNavigator {

    private int mCount = 0; //返回键计数器

    @NonNull
    @Override
    protected MainFragment createFragment() {
        return MainFragment.newInstance();
    }

    @NonNull
    @Override
    protected MainViewModel createViewModel() {
        return new MainViewModel();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) { //监控/拦截/屏蔽返回键
            if (mCount == 1) {
                this.finish();
            } else if (mCount == 0) {
                Toast.makeText(this, "再按一次退出" + getResources().getText(R.string.app_name),
                        Toast.LENGTH_SHORT).show();
                mCount++;
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void openSignInActivity() {
        SignInActivity.startActivity(this, SignInConstant.YES);
    }

    @Override
    public void openSignRecordActivity() {
        SignInActivity.startActivity(this, SignInConstant.NO);
    }

}
