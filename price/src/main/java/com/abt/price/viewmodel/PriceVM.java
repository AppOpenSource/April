package com.abt.price.viewmodel;

import com.abt.price.adapter.PriceAdapter;
import com.abt.price.base.BaseLoadListener;
import com.abt.price.bean.SimplePriceBean;
import com.abt.price.constant.MainConstant;
import com.abt.price.model.IPriceModel;
import com.abt.price.model.PriceModelImpl;
import com.abt.price.view.IPriceView;

import java.util.List;

/**
 * @描述： @PriceVM
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class PriceVM implements BaseLoadListener<SimplePriceBean> {
    private static final String TAG = "PriceVM";
    private IPriceModel mPriceModel;
    private IPriceView mPriceView;
    private PriceAdapter mAdapter;
    private int currPage = 1; //当前页数
    private int loadType; //加载数据的类型

    public PriceVM(IPriceView priceView, PriceAdapter adapter) {
        this.mPriceView = priceView;
        this.mAdapter = adapter;
        mPriceModel = new PriceModelImpl();
        getNewsData();
    }

    /**
     * 第一次获取新闻数据
     */
    private void getNewsData() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        mPriceModel.loadPriceData(currPage, this);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = MainConstant.LoadData.REFRESH;
        currPage = 1;
        mPriceModel.loadPriceData(currPage, this);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = MainConstant.LoadData.LOAD_MORE;
        currPage++;
        mPriceModel.loadPriceData(currPage, this);
    }

    @Override
    public void loadSuccess(List<SimplePriceBean> list) {
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
        mPriceView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mPriceView.loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mPriceView.loadComplete();
    }
}

