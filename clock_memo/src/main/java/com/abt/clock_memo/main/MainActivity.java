package com.abt.clock_memo.main;

import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

import com.abt.basic.arch.mvvm.view.BaseActivity;
import com.abt.basic.arch.mvvm.view.BaseFragment;

public class MainActivity extends BaseActivity {

    @NonNull
    @Override
    protected BaseFragment createFragment() {
        return MainFragment.newInstance();
    }

    @NonNull
    @Override
    protected BaseObservable createViewModel() {
        return new MainViewModel();
    }
}
