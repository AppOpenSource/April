package com.abt.clock_memo.ui.sign;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

import com.abt.basic.arch.mvvm.viewmodel.IViewModel;
import com.abt.clock_memo.base.BaseApplication;
import com.abt.clock_memo.bean.SignIn;
import com.abt.clock_memo.global.PreferenceConstant;
import com.abt.clock_memo.util.LocationUtil;
import com.abt.clock_memo.util.PreferencesUtil;

import java.lang.ref.WeakReference;

/**
 * @描述： @MainViewModel
 * @作者： @黄卫旗
 * @创建时间： @2018/5/28
 */
public class SignInViewModel extends BaseObservable implements IViewModel<SignInNavigator> {

    private static final String TAG = SignInViewModel.class.getSimpleName();
    public final ObservableField<String> text = new ObservableField<>();
    private WeakReference<SignInNavigator> mNavigator;
    private WeakReference<SignInContract.IView> mSignInView;

    @Override
    public void initialize() {
        text.set("click me!!");
    }

    @Override
    public void setNavigator(SignInNavigator signNavigator) {
        mNavigator = new WeakReference<>(signNavigator);
    }

    public final void setSignInView(SignInContract.IView view) {
        mSignInView = new WeakReference<>(view);
    }

    /*protected void onInitView() {
        mTitle = (TextView) findViewById(R.id.title_content);
        mTitle.setText("打卡记录");
    }*/

    public final void tryAgain() {
        mSignInView.get().showDeleteDialog();
        // 处理中 Signing
        LocationUtil util = LocationUtil.getInstance();
        boolean res = util.signIn(BaseApplication.getAppContext());
        Log.d(TAG, "signIn res: " + res);

        // 处理后
        SignIn in = new SignIn();
        long time = System.currentTimeMillis();
        String nickName = PreferencesUtil.getString(BaseApplication.getAppContext(), PreferenceConstant.SHARE_KEY_NICK_NAME);
        int count = PreferencesUtil.getInt(BaseApplication.getAppContext(), PreferenceConstant.SHARE_KEY_SIGN_IN_COUNT) + 1;
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
        /*if (mSignInList != null) {
            mSignInList.add(0, in);
            mAdapter.notifyDataSetChanged();
            FileManager.write(BaseApplication.getAppContext(), PreferenceConstant.FILE_NAME_SIGN_IN, mSignInList);
            //FileHelper.saveStorage2SDCard(mSignInList, GlobalConstant.FILE_NAME_SIGN_IN);
            Log.d(TAG, "mAdapter.notifyDataSetChanged(): " + mSignInList.size());
        }*/

        mSignInView.get().dismissDialog();
    }

}
