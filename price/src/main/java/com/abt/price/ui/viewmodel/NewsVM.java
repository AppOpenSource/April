package com.abt.price.ui.viewmodel;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.core.bean.news.SimpleNewsBean;
import com.abt.price.model.news.INewsModel;
import com.abt.price.model.news.NewsModelImpl;
import com.abt.price.ui.INewsView;
import com.abt.price.ui.adapter.NewsAdapter;
import com.abt.price.ui.constant.PageConstant;

import java.util.List;

/**
 * @描述： @NewsVM
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class NewsVM implements BaseLoadListener<SimpleNewsBean> {
    private static final String TAG = "NewsVM";
    private INewsModel mNewsModel;
    private INewsView mNewsView;
    private NewsAdapter mAdapter;
    private int currPage = 1; //当前页数
    private int loadType; //加载数据的类型

    public NewsVM(INewsView mNewsView, NewsAdapter mAdapter) {
        this.mNewsView = mNewsView;
        this.mAdapter = mAdapter;
        mNewsModel = new NewsModelImpl();
        getNewsData();
    }

    /**
     * 第一次获取新闻数据
     */
    private void getNewsData() {
        loadType = PageConstant.LoadData.FIRST_LOAD;
        mNewsModel.loadNewsData(currPage, this);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = PageConstant.LoadData.REFRESH;
        currPage = 1;
        mNewsModel.loadNewsData(currPage, this);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = PageConstant.LoadData.LOAD_MORE;
        currPage++;
        mNewsModel.loadNewsData(currPage, this);
    }

    @Override
    public void loadSuccess(List<SimpleNewsBean> list) {
        if (currPage > 1) {
            //上拉加载的数据
            mAdapter.loadMoreData(list);
        } else {
            //第一次加载或者下拉刷新的数据
            mAdapter.refreshData(list);
        }
    }

    @Override
    public void loadFailure(String message) {
        // 加载失败后的提示
        if (currPage > 1) {
            //加载失败需要回到加载之前的页数
            currPage--;
        }
        mNewsView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mNewsView.loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mNewsView.loadComplete();
    }
}

