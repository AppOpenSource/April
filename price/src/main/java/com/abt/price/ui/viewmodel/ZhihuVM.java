package com.abt.price.ui.viewmodel;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.ui.adapter.ZhihuAdapter;
import com.abt.price.bean.zhihu.SimpleZhihuBean;
import com.abt.price.ui.constant.PageConstant;
import com.abt.price.model.zhihu.IZhihuModel;
import com.abt.price.model.zhihu.ZhihuModelImpl;
import com.abt.price.ui.IZhihuView;

import java.util.List;

/**
 * @描述： @ZhihuVM
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class ZhihuVM implements BaseLoadListener<SimpleZhihuBean> {
    private static final String TAG = "ZhihuVM";
    private IZhihuModel mZhihuModel;
    private IZhihuView mZhihuView;
    private ZhihuAdapter mAdapter;
    private int currPage = 1; //当前页数
    private int loadType; //加载数据的类型

    public ZhihuVM(IZhihuView zhihuView, ZhihuAdapter mAdapter) {
        this.mZhihuView = zhihuView;
        this.mAdapter = mAdapter;
        mZhihuModel = new ZhihuModelImpl();
        getZhihuData();
    }

    /**
     * 第一次获取知乎数据
     */
    private void getZhihuData() {
        loadType = PageConstant.LoadData.FIRST_LOAD;
        mZhihuModel.loadZhihuData(currPage, this);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = PageConstant.LoadData.REFRESH;
        currPage = 1;
        mZhihuModel.loadZhihuData(currPage, this);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = PageConstant.LoadData.LOAD_MORE;
        currPage++;
        mZhihuModel.loadZhihuData(currPage, this);
    }

    @Override
    public void loadSuccess(List<SimpleZhihuBean> list) {
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
        mZhihuView.loadFailure(message);
    }

    @Override
    public void loadStart() {
        mZhihuView.loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mZhihuView.loadComplete();
    }
}

