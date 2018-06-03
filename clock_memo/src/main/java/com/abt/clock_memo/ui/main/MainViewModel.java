package com.abt.clock_memo.ui.main;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.abt.basic.arch.mvvm.viewmodel.IViewModel;
import com.abt.common.app.BasicApplication;
import com.abt.common.util.VersionUtils;

import java.lang.ref.WeakReference;

/**
 * @描述： @MainViewModel
 * @作者： @黄卫旗
 * @创建时间： @2018/5/28
 */
public class MainViewModel extends BaseObservable implements IViewModel<MainNavigator> {

    public final ObservableField<String> txt = new ObservableField<>();
    private WeakReference<MainNavigator> mNavigator;

    @Override
    public void initialize() {
        String code = VersionUtils.getVersionCode(BasicApplication.getAppContext());
        String name = VersionUtils.getVersionName(BasicApplication.getAppContext());
        txt.set("version_"+name+"_versionCode_"+code);
    }

    @Override
    public void setNavigator(MainNavigator mainNavigator) {
        mNavigator = new WeakReference<>(mainNavigator);
    }

    public final void clock() {
        mNavigator.get().openSignInActivity();
    }

    public final void clockMemo() {
        mNavigator.get().openSignRecordActivity();
    }

}
