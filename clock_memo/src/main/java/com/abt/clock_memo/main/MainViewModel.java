package com.abt.clock_memo.main;

import android.databinding.BaseObservable;
import android.databinding.ObservableField;

import com.abt.basic.arch.mvvm.view.INavigator;
import com.abt.basic.arch.mvvm.viewmodel.IViewModel;

/**
 * @描述： @MainViewModel
 * @作者： @黄卫旗
 * @创建时间： @2018/5/28
 */
public class MainViewModel extends BaseObservable implements IViewModel {

    public final ObservableField<String> text = new ObservableField<>();

    private boolean flag = false;

    @Override
    public void initialize() {
        text.set("click me!!");
    }

    @Override
    public void setNavigator(INavigator navigator) {

    }

    public  void onClick() {
        if (flag) {
            flag = false;
            text.set("click me!!");
        } else {
            flag = true;
            text.set("click again!!");
        }
    }

}
