package com.abt.price.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abt.basic.arch.mvvm.viewmodel.IViewModel;
import com.abt.common.helper.DialogHelper;
import com.abt.common.util.ToastUtils;
import com.abt.price.R;
import com.abt.price.databinding.ActivityZhihuBinding;
import com.abt.price.ui.IZhihuView;
import com.abt.price.ui.adapter.ZhihuAdapter;
import com.abt.price.ui.viewmodel.ZhihuVM;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import static com.abt.price.ui.constant.PageConstant.LoadData.FIRST_LOAD;

/**
 * @描述： @ZhihuFragment
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class ZhihuFragment extends Fragment implements IZhihuView,
        XRecyclerView.LoadingListener {

    private ActivityZhihuBinding binding;
    private ZhihuAdapter zhihuAdapter;
    private ZhihuVM zhihuVM;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        binding = ActivityZhihuBinding.inflate(inflater, container, false);
        initRecyclerView();
        zhihuVM = new ZhihuVM(this, zhihuAdapter);
        zhihuAdapter.setZhihuVM(zhihuVM);
        return binding.getRoot();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        binding.newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        binding.newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        binding.newsRv.setArrowImageView(R.mipmap.pull_down_arrow);
        binding.newsRv.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        binding.newsRv.setLayoutManager(layoutManager);
        zhihuAdapter = new ZhihuAdapter(this.getActivity());
        binding.newsRv.setAdapter(zhihuAdapter);
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        zhihuVM.loadRefreshData();
    }

    @Override
    public void onLoadMore() {
        //上拉加载更多
        zhihuVM.loadMoreData();
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
        binding.newsRv.loadMoreComplete(); //结束加载
        binding.newsRv.refreshComplete(); //结束刷新
    }

    @Override
    public void loadFailure(String message) {
        DialogHelper.getInstance().close();
        binding.newsRv.loadMoreComplete(); //结束加载
        binding.newsRv.refreshComplete(); //结束刷新
        ToastUtils.show(this.getActivity(), message);
    }

    @Override
    public void setViewModel(IViewModel iViewModel) {

    }

    @Override
    public void setToolbarViewModel(Object o) {

    }
}
