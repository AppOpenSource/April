package com.abt.price.ui.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.abt.basic.arch.mvvm.viewmodel.IViewModel;
import com.abt.price.R;
import com.abt.price.databinding.ActivityZhihuBinding;
import com.abt.price.ui.IGankView;
import com.abt.price.ui.adapter.GankAdapter;
import com.abt.price.ui.viewmodel.GankVM;

/**
 * @描述： @GankFragment
 * @作者： @黄卫旗
 * @创建时间： @11/06/2018
 */
public class GankFragment extends Fragment implements IGankView {

    private ActivityZhihuBinding binding;
    private GridLayoutManager gridLayoutManager;
    private GankAdapter gankAdapter;
    private GankVM gankVM;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding = DataBindingUtil.setContentView(this.getActivity(), R.layout.fragment_gank);
        gridLayoutManager = new GridLayoutManager(getContext(),2);
        binding.newsRv.setLayoutManager(gridLayoutManager);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        gankAdapter = new GankAdapter(this.getActivity());
        gankVM = new GankVM(this, gankAdapter);
        gankAdapter.setGankVM(gankVM);
    }

    @Override
    public void loadStart(int i) {

    }

    @Override
    public void loadComplete() {

    }

    @Override
    public void loadFailure(String s) {

    }

    @Override
    public void setViewModel(IViewModel iViewModel) {

    }

    @Override
    public void setToolbarViewModel(Object o) {

    }
}
