package com.abt.price.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;

import com.abt.basic.arch.mvvm.viewmodel.IViewModel;
import com.abt.common.helper.DialogHelper;
import com.abt.common.util.ToastUtils;
import com.abt.price.R;
import com.abt.price.adapter.PriceAdapter;
import com.abt.price.databinding.ActivityMainBinding;
import com.abt.price.viewmodel.PriceVM;
import com.abt.price.widget.RecyclerViewDivider;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import static com.abt.price.constant.MainConstant.LoadData.FIRST_LOAD;

/**
 * @描述： @PriceActivity
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class PriceActivity extends AppCompatActivity implements IPriceView,
        XRecyclerView.LoadingListener {

    private Context mContext;
    private ActivityMainBinding binding;
    private PriceAdapter priceAdapter; //新闻列表的适配器
    private PriceVM priceVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mContext = this;
        initRecyclerView();
        priceVM = new PriceVM(this, priceAdapter);
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

        //添加自定义的分割线
        RecyclerViewDivider divider = new RecyclerViewDivider(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.custom_divider));
        binding.newsRv.addItemDecoration(divider);
        //添加Android自带的分割线
        //binding.newsRv.addItemDecoration(new RecyclerViewDivider(this,DividerItemDecoration.VERTICAL));

        priceAdapter = new PriceAdapter(this);
        binding.newsRv.setAdapter(priceAdapter);
    }

    @Override
    public void onRefresh() {
        //下拉刷新
        priceVM.loadRefreshData();
    }

    @Override
    public void onLoadMore() {
        //上拉加载更多
        priceVM.loadMoreData();
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
