package com.abt.clock_memo.ui.sign;

import android.content.res.Resources;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.util.Log;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.basic.arch.mvvm.viewmodel.IViewModel;
import com.abt.clock_memo.R;
import com.abt.clock_memo.base.BaseApplication;
import com.abt.clock_memo.bean.SignIn;
import com.abt.clock_memo.global.MainConstant;
import com.abt.clock_memo.global.PreferenceConstant;
import com.abt.clock_memo.model.ISignInModel;
import com.abt.clock_memo.model.SignInModelImpl;
import com.abt.clock_memo.ui.adapter.SignInAdapter;
import com.abt.clock_memo.util.LocationUtil;
import com.abt.clock_memo.util.PreferencesUtil;

import java.lang.ref.WeakReference;
import java.util.List;

/**
 * @描述： @SignInViewModel
 * @作者： @黄卫旗
 * @创建时间： @2018/5/28
 */
public class SignInViewModel extends BaseObservable implements IViewModel<SignInNavigator>, BaseLoadListener<SignIn> {

    private static final String TAG = SignInViewModel.class.getSimpleName();
    public final ObservableField<String> text = new ObservableField<>();
    private WeakReference<SignInNavigator> mNavigator;
    private WeakReference<SignInContract.IView> mSignInView;
    private ISignInModel mSignInModel;
    private SignInAdapter mAdapter;
    private int currPage = 1; //当前页数
    private int loadType; //加载数据的类型

    public SignInViewModel() {
        mSignInModel = new SignInModelImpl();
        getNewsData();
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

    public final void tryAgain() {
        mSignInView.get().showDeleteDialog();
        // 处理中 Signing
        LocationUtil util = LocationUtil.getInstance();
        boolean res = util.signIn(BaseApplication.getAppContext());
        Log.d(TAG, "signIn res: " + res);

        // 处理后
        SignIn in = new SignIn();
        long time = System.currentTimeMillis();
        String nickName = PreferencesUtil.getString(BaseApplication.getAppContext(), PreferenceConstant.SHARE_KEY_NICK_NAME);
        int count = PreferencesUtil.getInt(BaseApplication.getAppContext(), PreferenceConstant.SHARE_KEY_SIGN_IN_COUNT) + 1;
        if (TextUtils.isEmpty(nickName)) {
            nickName = "第" + count + "条记录";
        }
        in.setName(nickName);
        in.setTime(time + "");
        if (res) { // 打卡成功
            // TODO 更新打卡记录
            in.setStatus("刷卡成功");
        } else {
            in.setStatus("刷卡失败");
            //mSignInBtn.setVisibility(View.VISIBLE);
        }
        /*if (mSignInList != null) {
            mSignInList.add(0, in);
            mAdapter.notifyDataSetChanged();
            FileManager.write(BaseApplication.getAppContext(), PreferenceConstant.FILE_NAME_SIGN_IN, mSignInList);
            //FileHelper.saveStorage2SDCard(mSignInList, GlobalConstant.FILE_NAME_SIGN_IN);
            Log.d(TAG, "mAdapter.notifyDataSetChanged(): " + mSignInList.size());
        }*/

        mSignInView.get().dismissDialog();
    }

    /**
     * 第一次获取新闻数据
     */
    private void getNewsData() {
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
        mSignInView.get().loadStart(loadType);
    }

    @Override
    public void loadComplete() {
        mSignInView.get().loadComplete();
    }
}
