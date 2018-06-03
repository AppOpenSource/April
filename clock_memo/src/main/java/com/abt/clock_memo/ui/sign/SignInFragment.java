package com.abt.clock_memo.ui.sign;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.abt.basic.arch.mvvm.view.BaseFragment;
import com.abt.basic.arch.mvvm.viewmodel.ToolbarViewModel;
import com.abt.clock_memo.R;
import com.abt.clock_memo.databinding.FragmentSigninBinding;
import com.abt.clock_memo.helper.DialogHelper;
import com.abt.clock_memo.ui.sign.adapter.SignInAdapter;
import com.abt.clock_memo.util.ToastUtils;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import static com.abt.clock_memo.global.MainConstant.LoadData.FIRST_LOAD;

/**
 * @描述： @SignInFragment
 * @作者： @黄卫旗
 * @创建时间： @2018/5/28
 */
public class SignInFragment extends BaseFragment<SignInViewModel,
        ToolbarViewModel> implements SignInContract.IView, XRecyclerView.LoadingListener {

    private static final String TAG = SignInFragment.class.getSimpleName();
    private FragmentSigninBinding mBinding;
    private SignInAdapter mSignInAdapter;
    private ProgressDialog mDialog;

    /**
     * 返回实例
     */
    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel.initialize();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentSigninBinding.inflate(inflater, container, false);
        mBinding.setSignInVM(mViewModel);
        mBinding.setToolbarVM(mToolbarModel);
        mViewModel.setSignInView(this);
        mViewModel.getClockMemoData();
        initRecyclerView();
        mViewModel.setAdapter(mSignInAdapter);
        return mBinding.getRoot();
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        mBinding.newsRv.setRefreshProgressStyle(ProgressStyle.BallClipRotate); //设置下拉刷新的样式
        mBinding.newsRv.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate); //设置上拉加载更多的样式
        mBinding.newsRv.setArrowImageView(R.mipmap.pull_down_arrow);
        mBinding.newsRv.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        mBinding.newsRv.setLayoutManager(layoutManager);
        mSignInAdapter = new SignInAdapter(this.getActivity());
        mBinding.newsRv.setAdapter(mSignInAdapter);
    }

    @Override
    public void showLoadingDialog() {
        // 处理前 Loading
        mDialog = new ProgressDialog(this.getActivity());
        mDialog.setTitle("正在打卡，请稍候...");
        mDialog.setCancelable(true);
        mDialog.show();
    }

    @Override
    public void dismissDialog() {
        if (mDialog != null) {
            this.getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mDialog.dismiss();
                }
            });
        }
    }

    @Override
    public void showMoreDialog() {

    }

    @Override
    public void onRefresh() {
        //下拉刷新
        mViewModel.loadRefreshData();
    }

    @Override
    public void onLoadMore() {
        //上拉加载更多
        mViewModel.loadMoreData();
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
        mBinding.newsRv.loadMoreComplete(); //结束加载
        mBinding.newsRv.refreshComplete(); //结束刷新
    }

    @Override
    public void loadFailure(String message) {
        DialogHelper.getInstance().close();
        mBinding.newsRv.loadMoreComplete(); //结束加载
        mBinding.newsRv.refreshComplete(); //结束刷新
        ToastUtils.show(this.getActivity(), message);
    }

}
