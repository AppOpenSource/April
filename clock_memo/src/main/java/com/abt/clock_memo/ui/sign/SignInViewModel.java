package com.abt.clock_memo.ui.sign;

import android.content.Intent;
import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.basic.arch.mvvm.viewmodel.IViewModel;
import com.abt.clock_memo.R;
import com.abt.clock_memo.base.BaseApplication;
import com.abt.clock_memo.bean.SignIn;
import com.abt.clock_memo.global.MainConstant;
import com.abt.clock_memo.global.PreferenceConstant;
import com.abt.clock_memo.global.SignInConstant;
import com.abt.clock_memo.model.ISignInModel;
import com.abt.clock_memo.model.SignInModelImpl;
import com.abt.clock_memo.ui.adapter.SignInAdapter;
import com.abt.clock_memo.ui.listener.SignInListener;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @描述： @SignInViewModel
 * @作者： @黄卫旗
 * @创建时间： @2018/5/28
 */
public class SignInViewModel extends BaseObservable implements
        IViewModel<SignInNavigator>, BaseLoadListener<SignIn>,
        SignInListener<SignIn> {

    private static final String TAG = SignInViewModel.class.getSimpleName();
    public final ObservableField<String> text = new ObservableField<>();
    private WeakReference<SignInNavigator> mNavigator;
    private WeakReference<SignInContract.IView> mSignInView;
    private ISignInModel mSignInModel;
    private SignInAdapter mAdapter;
    private int currPage = 1; //当前页数
    private int loadType; //加载数据的类型
    private boolean mInit = false;
    private String mSignIn;

    public SignInViewModel(Intent intent) {
        mSignIn = intent.getExtras().getString(PreferenceConstant.SIGN_IN_OR_NOT);
        if (TextUtils.isEmpty(mSignIn)) {
            mSignIn = SignInConstant.NO;
        }
        mSignInModel = new SignInModelImpl();
        // getClockMemoData();
    }

    @Override
    public void initialize() {
        Resources resources = BaseApplication.getAppContext().getResources();
        text.set(resources.getString(R.string.clock_record));
    }

    @Override
    public void setNavigator(SignInNavigator signNavigator) {
        mNavigator = new WeakReference<>(signNavigator);
    }

    public final void setSignInView(SignInContract.IView view) {
        mSignInView = new WeakReference<>(view);
    }

    public final void setAdapter(SignInAdapter adapter) {
        mAdapter = adapter;
    }

    /**
     * 第一次获取打卡数据
     */
    public void getClockMemoData() {
        loadType = MainConstant.LoadData.FIRST_LOAD;
        mSignInModel.loadClockData(currPage, this);
    }

    /**
     * 获取下拉刷新的数据
     */
    public void loadRefreshData() {
        loadType = MainConstant.LoadData.REFRESH;
        currPage = 1;
        mSignInModel.loadClockData(currPage, this);
    }

    /**
     * 获取上拉加载更多的数据
     */
    public void loadMoreData() {
        loadType = MainConstant.LoadData.LOAD_MORE;
        currPage++;
        mSignInModel.loadClockData(currPage, this);
    }

    @Override
    public void loadSuccess(List<SignIn> list) {
        if (currPage > 1) {
            //上拉加载的数据
            mAdapter.loadMoreData(list);
        } else {
            //第一次加载或者下拉刷新的数据
            mAdapter.refreshData(list);
        }

        if (!mInit) {
            mInit = true; // 第一次进入，有可能是打卡，也有是浏览记录
            mSignInModel.sign(mSignIn,this);
        } else { // 重新加载，不算打卡
            mSignInModel.sign(SignInConstant.NO,this);
        }
    }

    @Override
    public void loadFailure(String message) {
        // 加载失败后的提示
        if (currPage > 1) {
            //加载失败需要回到加载之前的页数
            currPage--;
        }
        mSignInView.get().loadFailure(message);
    }

    @Override
    public void loadStart() {
        if (!TextUtils.isEmpty(mSignIn) && mSignIn.equalsIgnoreCase(SignInConstant.YES)) {
            mSignInView.get().showLoadingDialog();
            return; // 打卡有自己的Dialog，不用显示加载数据的Dialog
        }
        mSignInView.get().loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mSignInView.get().loadComplete();
    }

    @Override
    public void signInSuccess(List<SignIn> list) {
        if (currPage > 1) {
            //上拉加载的数据
            mAdapter.loadMoreData(list);
        } else {
            //第一次加载或者下拉刷新的数据
            mAdapter.refreshData(list);
        }
        mSignInView.get().dismissDialog();
    }

    @Override
    public void signInFailure(String var1) {

    }

    @Override
    public void signInStart() {
        if (!TextUtils.isEmpty(mSignIn) && mSignIn.equalsIgnoreCase(SignInConstant.YES)) {
            return; // 已经在loadStart中show了，这里可以直接返回
        }
        mSignInView.get().showLoadingDialog();
    }

    @Override
    public void signInComplete() {
        mSignIn = SignInConstant.NO; // 重置状态，避免下拉加载更多时进入loadStart中的showLoadingDialog
    }
}
