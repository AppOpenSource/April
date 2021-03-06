package com.abt.price.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abt.basic.arch.mvvm.viewmodel.IViewModel;
import com.abt.common.helper.DialogHelper;
import com.abt.common.util.ToastUtils;
import com.abt.price.app.PriceApp;
import com.abt.price.base.BaseFragment;
import com.abt.price.core.bean.gank.Gank;
import com.abt.price.databinding.FragmentGankBinding;
import com.abt.price.di.component.AppComponent;
import com.abt.price.di.component.DaggerAppComponent;
import com.abt.price.di.module.AppModule;
import com.abt.price.di.module.GankApiModule;
import com.abt.price.ui.IGankView;
import com.abt.price.ui.adapter.GankAdapter;
import com.abt.price.ui.viewmodel.GankVM;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import javax.inject.Inject;

import static com.abt.price.ui.constant.PageConstant.LoadData.FIRST_LOAD;

/**
 * @描述： @GankFragment
 * @作者： @黄卫旗
 * @创建时间： @11/06/2018
 */
public class GankFragment extends BaseFragment implements IGankView,
        XRecyclerView.LoadingListener {

    private static final String TAG = GankFragment.class.getSimpleName();
    private FragmentGankBinding binding;
    private GridLayoutManager gridLayoutManager;
    @Inject
    GankAdapter gankAdapter;
    @Inject
    GankVM gankVM;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        DaggerAppComponent.builder()
                .gankApiModule(new GankApiModule(this))
                .appModule(new AppModule(this.getActivity()))
                .build()
                .inject(this);

        binding = FragmentGankBinding.inflate(inflater, container, false);
        gridLayoutManager = new GridLayoutManager(this.getActivity(), 2);
        binding.contentRv.setLayoutManager(gridLayoutManager);
        binding.contentRv.setLoadingListener(this);

        //gankAdapter = new GankAdapter(this.getActivity());
        binding.contentRv.setAdapter(gankAdapter);

        //gankVM = new GankVM(this, gankAdapter);
        gankAdapter.setGankVM(gankVM);
        return binding.getRoot();
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        gankVM.loadRefreshData();
    }

    @Override
    public void onLoadMore() {
        //上拉加载更多
        gankVM.loadMoreData();
    }

    @Override
    public void loadStart(int loadType) {
        if (loadType == FIRST_LOAD) {
            DialogHelper.getInstance().show(this.getActivity(), "加载中...");
        }
    }

    @Override
    public void loadComplete() {
        DialogHelper.getInstance().close();
        binding.contentRv.loadMoreComplete(); //结束加载
        binding.contentRv.refreshComplete(); //结束刷新
    }

    @Override
    public void loadFailure(String message) {
        DialogHelper.getInstance().close();
        binding.contentRv.loadMoreComplete(); //结束加载
        binding.contentRv.refreshComplete(); //结束刷新
        ToastUtils.show(this.getActivity(), message);
    }

    @Override
    public void setViewModel(IViewModel iViewModel) {

    }

    @Override
    public void setToolbarViewModel(Object o) {

    }

}
