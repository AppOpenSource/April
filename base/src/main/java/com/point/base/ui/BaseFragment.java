package com.point.base.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.point.base.model.control.BasePresenter;
import com.point.base.model.control.BaseView;
import com.point.base.model.control.LogicProxy;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public abstract class BaseFragment extends Fragment {

    protected View mRootView;
    protected BasePresenter mPresenter;
    //private LoadingView mLoginView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutResource(), container, false);
        }
        onInitView(mRootView);
        return mRootView;
        // ButterKnife.bind(this, mRootView);
        // mLoginView = new LoadingView(getActivity());
    }

    protected abstract int getLayoutResource();

    protected abstract void onInitView(View view);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // ButterKnife.unbind(this);
        if (mPresenter != null)
            mPresenter.detachView();
    }

    //获得该页面的实例
    public <T> T getLogicImpl(Class cls, BaseView o) {
        return LogicProxy.getInstance().bind(cls, o);
    }

    public void showLoadingView() {
        // mLoginView.show();
    }

    public void hideLoadingView() {
        // mLoginView.hide();
    }

}
