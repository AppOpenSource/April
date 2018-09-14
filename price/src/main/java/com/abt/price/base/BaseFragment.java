package com.abt.price.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.abt.price.app.PriceApp;
import com.abt.price.di.component.AppComponent;

/**
 * @描述： @BaseFragment
 * @作者： @黄卫旗
 * @创建时间： @14/09/2018
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(PriceApp.getsInstance().getAppComponent());
    }

    protected abstract void setupComponent(AppComponent appComponent);

}
