package com.point.april.ui.activity.news;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.point.april.R;
import com.point.april.bean.WeixinNews;
import com.point.april.data.PreferencesUtil;
import com.point.april.global.GlobalConstant;
import com.point.april.presenter.IWeixinPresenter;
import com.point.april.presenter.WeiXinPresenterImpl;
import com.point.april.ui.activity.BaseActivity;
import com.point.april.ui.adapter.WeixinAdapter;
import com.point.april.ui.iView.IWeixinFragment;
import com.point.april.util.NetWorkUtil;
import com.point.april.util.SharePreferenceUtil;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 微信精选页面
 */
public class WeiXinActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener,
        IWeixinFragment, View.OnClickListener {

    private static final String TAG = WeiXinActivity.class.getSimpleName();
    private WeixinAdapter weixinAdapter;
    //@BindView(R.id.swipe_target)
    private RecyclerView swipeTarget;
    //@BindView(R.id.swipeToLoadLayout)
    private SwipeRefreshLayout swipeRefreshLayout;
    //@BindView(R.id.progressBar)
    //private ProgressBar progressBar;
    private TextView mTitle;
    private LinearLayout mGoBack;

    private Unbinder mUnbinder;
    private IWeixinPresenter mWeixinPresenter;
    private ArrayList<WeixinNews> weixinNewses = new ArrayList<>();
    private int currentPage = 1;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean loading = false;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weixin);

        ButterKnife.bind(this);
        initData();
        initView();

        // 记录打开新闻精选次数
        int count = PreferencesUtil.getInt(this, GlobalConstant.SHARE_KEY_OPEN_NEWS_COUNT);
        PreferencesUtil.write(this, GlobalConstant.SHARE_KEY_OPEN_NEWS_COUNT, ++count);
    }

    private void initView() {
        mTitle = (TextView) findViewById(R.id.title_content);
        mTitle.setText("微信精选");
        mGoBack = (LinearLayout) findViewById(R.id.go_back);
        mGoBack.setOnClickListener(this);

        //progressBar = (ProgressBar) findViewById(R.id.progressBar);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToLoadLayout);
        swipeTarget = (RecyclerView) findViewById(R.id.swipe_target);

        showProgressDialog();

        swipeRefreshLayout.setOnRefreshListener(this);
        //setSwipeRefreshLayoutColor(swipeRefreshLayout);
        mLinearLayoutManager = new LinearLayoutManager(this);
        swipeTarget.setLayoutManager(mLinearLayoutManager);
        swipeTarget.setHasFixedSize(true);

        swipeTarget.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) { // 向下滚动
                    visibleItemCount = mLinearLayoutManager.getChildCount();
                    totalItemCount = mLinearLayoutManager.getItemCount();
                    pastVisiblesItems = mLinearLayoutManager.findFirstVisibleItemPosition();

                    if (!loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = true;
                            onLoadMore();
                        }
                    }
                }
            }
        });

        weixinAdapter = new WeixinAdapter(this, weixinNewses);
        swipeTarget.setAdapter(weixinAdapter);
        mWeixinPresenter.getWeixinNews(1);

        if (SharePreferenceUtil.isRefreshOnlyWifi(this)) {
            if (NetWorkUtil.isWifiConnected(this)) {
                onRefresh();
            } else {
                Toast.makeText(this, getString(R.string.toast_wifi_refresh_data), Toast.LENGTH_SHORT).show();
            }
        } else {
            onRefresh();
        }
    }

    private void initData() {
        mWeixinPresenter = new WeiXinPresenterImpl(this, this);
    }

    public void onLoadMore() {
        mWeixinPresenter.getWeixinNews(currentPage);
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();

        currentPage = 1;
        weixinNewses.clear();
        //2016-04-05修复Inconsistency detected. Invalid view holder adapter positionViewHolder
        weixinAdapter.notifyDataSetChanged();
        mWeixinPresenter.getWeixinNews(currentPage);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWeixinPresenter.unsubcrible();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    @Override
    public void onRefresh() {
        currentPage = 1;
        weixinNewses.clear();
        //2016-04-05修复Inconsistency detected. Invalid view holder adapter positionViewHolder
        weixinAdapter.notifyDataSetChanged();
        mWeixinPresenter.getWeixinNews(currentPage);
    }

    @Override
    public void updateList(ArrayList<WeixinNews> weixinNewsList) {
        if (weixinNewsList!=null) {
            Log.d(TAG, "updateList() weixinNewsList: "+weixinNewsList.size());
        }
        currentPage++;
        weixinNewses.addAll(weixinNewsList);
        weixinAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressDialog() {
        Log.d(TAG, "showProgressDialog: ");
        //if (progressBar != null)
        //    progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hidProgressDialog() {
        Log.d(TAG, "hidProgressDialog: ");
        if (swipeRefreshLayout != null) {//不加可能会崩溃
            swipeRefreshLayout.setRefreshing(false);
            loading = false;
        }
       // if (progressBar != null)
       //     progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError(String error) {
        Log.d(TAG, "showError: ");
        if (swipeTarget != null) {
            mWeixinPresenter.getWeixinNewsFromCache(currentPage);
            Snackbar.make(swipeTarget, getString(R.string.common_loading_error) + error, Snackbar.LENGTH_INDEFINITE).setAction("重试", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWeixinPresenter.getWeixinNews(currentPage);
                }
            }).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.go_back:
                this.finish();
            break;
        }
    }
}
