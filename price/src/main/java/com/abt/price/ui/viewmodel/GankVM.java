package com.abt.price.ui.viewmodel;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.core.bean.gank.Gank;
import com.abt.price.model.gank.GankModelImpl;
import com.abt.price.model.gank.IGankModel;
import com.abt.price.ui.IGankView;
import com.abt.price.ui.adapter.GankAdapter;
import com.abt.price.ui.constant.PageConstant;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Inject;

/**
 * @描述： @GankVM
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class GankVM implements BaseLoadListener<Gank> {
    private static final String TAG = "GankVM";
    private IGankModel mGankModel;
    private WeakReference<IGankView> mGankView;
    private GankAdapter mAdapter;
    private int currPage = 1; //当前页数
    private int loadType; //加载数据的类型

    @Inject
    public GankVM(IGankView gankView, GankAdapter adapter) {
        this.mGankView = new WeakReference<>(gankView);
        this.mAdapter = adapter;
        mGankModel = new GankModelImpl();
        getMeizhiData();
    }

    /**
     * 第一次获取知乎数据
     */
    private void getMeizhiData() {
        loadType = PageConstant.LoadData.FIRST_LOAD;
        mGankModel.loadMeizhiData(currPage, this);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = PageConstant.LoadData.REFRESH;
        currPage = 1;
        mGankModel.loadMeizhiData(currPage, this);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = PageConstant.LoadData.LOAD_MORE;
        currPage++;
        mGankModel.loadMeizhiData(currPage, this);
    }

    @Override
    public void loadSuccess(List<Gank> list) {
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
        if (null != mGankView.get()) {
            mGankView.get().loadFailure(message);
        }
    }

    @Override
    public void loadStart() {
        if (null != mGankView.get()) {
            mGankView.get().loadStart(loadType);
        }
    }

    @Override
    public void loadComplete() {
        if (null != mGankView) {
            mGankView.get().loadComplete();
        }
    }

}

