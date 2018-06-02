package com.abt.clock_memo.ui.main;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.abt.basic.arch.mvvm.viewmodel.IViewModel;

import java.lang.ref.WeakReference;

/**
 * @描述： @MainViewModel
 * @作者： @黄卫旗
 * @创建时间： @2018/5/28
 */
public class MainViewModel extends BaseObservable implements IViewModel<MainNavigator> {

    public final ObservableField<String> text = new ObservableField<>();
    private WeakReference<MainNavigator> mNavigator;

    @Override
    public void initialize() {
        text.set("click me!!");
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
