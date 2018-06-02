package com.abt.clock_memo.ui.sign;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abt.basic.arch.mvvm.view.BaseFragment;
import com.abt.basic.arch.mvvm.viewmodel.ToolbarViewModel;
import com.abt.clock_memo.databinding.FragmentSigninBinding;

/**
 * @描述： @SignInFragment
 * @作者： @黄卫旗
 * @创建时间： @2018/5/28
 */
public class SignInFragment extends BaseFragment<SignInViewModel,
        ToolbarViewModel> implements SignInContract.IView {

    private static final String TAG = SignInFragment.class.getSimpleName();
    private FragmentSigninBinding mFragmentSigninBinding;
    private ProgressDialog mDialog;

    /**
     * 返回实例
     */
    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.initialize();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mFragmentSigninBinding = FragmentSigninBinding.inflate(inflater, container, false);
        mFragmentSigninBinding.setSignInVM(mViewModel);
        mFragmentSigninBinding.setToolbarVM(mToolbarModel);
        mViewModel.setSignInView(this);
        return mFragmentSigninBinding.getRoot();
    }

    @Override
    public void showDeleteDialog() {
        Log.d(TAG, "handleSignIn()");
        // 处理前 Loading
        mDialog = new ProgressDialog(this.getActivity());
        mDialog.setTitle("正在打卡，请稍候...");
        mDialog.setCancelable(true);
        mDialog.show();
        Log.d(TAG, "Dialog showing...");
    }

    @Override
    public void dismissDialog() {
        if (mDialog != null) {
            this.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mDialog.dismiss();
                }
            });
        }
    }

    @Override
    public void showMoreDialog() {

    }
}
