package com.abt.price.ui.viewmodel;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.bean.zhihu.Stories;
import com.abt.price.model.zhihu.IZhihuModel;
import com.abt.price.model.zhihu.ZhihuModelImpl;
import com.abt.price.ui.IZhihuWebView;
import com.abt.price.ui.constant.PageConstant;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @描述： @ZhihuWebVM
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class ZhihuWebVM implements BaseLoadListener<Stories> {
    private static final String TAG = "ZhihuWebVM";
    private IZhihuModel mZhihuModel;
    private WeakReference<IZhihuWebView> mZhihuView;
    private int currPage = 1; //当前页数
    private int loadType; //加载数据的类型

    public ZhihuWebVM(IZhihuWebView zhihuWebView) {
        this.mZhihuView = new WeakReference<>(zhihuWebView);
        mZhihuModel = new ZhihuModelImpl();
        getZhihuData();
    }

    /**
     * 第一次获取知乎数据
     */
    private void getZhihuData() {
        loadType = PageConstant.LoadData.FIRST_LOAD;
        mZhihuModel.getLatestNews(currPage, this);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = PageConstant.LoadData.REFRESH;
        currPage = 1;
        mZhihuModel.getLatestNews(currPage, this);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = PageConstant.LoadData.LOAD_MORE;
        currPage++;
        mZhihuModel.getBeforeNews(currPage, this);
    }

    @Override
    public void loadSuccess(List<Stories> list) {
        if (currPage > 1) {
            //上拉加载的数据
            //mAdapter.loadMoreData(list);
        } else {
            //第一次加载或者下拉刷新的数据
            //mAdapter.refreshData(list);
        }
    }

    @Override
    public void loadFailure(String message) {
        // 加载失败后的提示
        if (currPage > 1) {
            //加载失败需要回到加载之前的页数
            currPage--;
        }
        if (null != mZhihuView.get()) {
            mZhihuView.get().loadFailure(message);
        }
    }

    @Override
    public void loadStart() {
        if (null != mZhihuView.get()) {
            mZhihuView.get().loadStart(loadType);
        }
    }

    @Override
    public void loadComplete() {
        if (null != mZhihuView) {
            mZhihuView.get().loadComplete();
        }
    }
}

