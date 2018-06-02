package com.abt.clock_memo.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abt.basic.arch.mvvm.view.BaseFragment;
import com.abt.basic.arch.mvvm.viewmodel.ToolbarViewModel;
import com.abt.clock_memo.databinding.FragmentMainBinding;

/**
 * @描述： @MainFragment
 * @作者： @黄卫旗
 * @创建时间： @2018/5/28
 */
public class MainFragment extends BaseFragment<MainViewModel, ToolbarViewModel> {

    private FragmentMainBinding mFragmentMainBinding;

    /**
     * 返回实例
     */
    public static MainFragment newInstance() {
        return new MainFragment();
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
        mFragmentMainBinding = FragmentMainBinding.inflate(inflater, container, false);
        mFragmentMainBinding.setMainVM(mViewModel);
        mFragmentMainBinding.setToolbarVM(mToolbarModel);
        return mFragmentMainBinding.getRoot();
    }

}
