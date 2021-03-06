package com.abt.price.ui.activity;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.abt.basic.arch.mvvm.viewmodel.IViewModel;
import com.abt.common.helper.DialogHelper;
import com.abt.common.util.ToastUtils;
import com.abt.price.R;
import com.abt.price.base.BaseActivity;
import com.abt.price.databinding.ActivityNewsBinding;
import com.abt.price.ui.INewsView;
import com.abt.price.ui.adapter.NewsAdapter;
import com.abt.price.ui.viewmodel.NewsVM;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import static com.abt.price.ui.constant.PageConstant.LoadData.FIRST_LOAD;

/**
 * @描述： @NewsActivity
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class NewsActivity extends BaseActivity implements INewsView,
        XRecyclerView.LoadingListener {

    private Context mContext;
    private ActivityNewsBinding binding;
    private NewsAdapter newsAdapter; //新闻列表的适配器
    private NewsVM newsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news);
        mContext = this;
        initRecyclerView();
        newsVM = new NewsVM(this, newsAdapter);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        binding.newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        binding.newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        binding.newsRv.setArrowImageView(R.mipmap.pull_down_arrow);
        binding.newsRv.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.newsRv.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(this);
        binding.newsRv.setAdapter(newsAdapter);
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        newsVM.loadRefreshData();
    }

    @Override
    public void onLoadMore() {
        //上拉加载更多
        newsVM.loadMoreData();
    }

    @Override
    public void loadStart(int loadType) {
        if (loadType == FIRST_LOAD) {
            DialogHelper.getInstance().show(mContext, "加载中...");
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
        ToastUtils.show(mContext, message);
    }

    @Override
    public void setViewModel(IViewModel iViewModel) {

    }

    @Override
    public void setToolbarViewModel(Object o) {

    }
}
