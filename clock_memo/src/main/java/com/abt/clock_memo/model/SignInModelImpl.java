package com.abt.clock_memo.model;

import android.os.Handler;
import android.util.Log;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.clock_memo.bean.SignIn;

import java.util.ArrayList;
import java.util.List;

import static com.abt.clock_memo.test.MockData.getRecordList;

/**
 * @描述： @SignInModelImpl
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class SignInModelImpl implements ISignInModel {

    private static final String TAG = "SignInModelImpl";
    List<SignIn> mSignInList = new ArrayList<SignIn>();

    @Override
    public void loadClockData(final int page, final BaseLoadListener<SignIn> loadListener) {
        mSignInList = getRecordList();
        //mSignInList = FileHelper.getStorageEntities(PreferenceConstant.FILE_NAME_SIGN_IN);
        //mSignInList = FileManager.read(BaseApplication.getAppContext(), PreferenceConstant.FILE_NAME_SIGN_IN);
        if (mSignInList != null) {
            Log.d(TAG, "mSignInList: " + mSignInList.size());
            for (int i = 0; i < mSignInList.size(); i++) {
                Log.d(TAG, "mSignInList(" + i + ")-->time: " + mSignInList.get(i).getTime());
            }
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadListener.loadSuccess(mSignInList);
                loadListener.loadComplete();
            }
        }, 1000);
        //mAdapter = new SignInAdapter(this, mSignInList);

        /*HttpUtils.getNewsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsBean>() {
                    @Override
                    public void onNext(@NonNull NewsBean newsBean) {
                        Log.i(TAG, "onNext: ");
                        List<NewsBean.OthersBean> othersBeanList = newsBean.getOthers();
                        signInList.clear();
                        if (othersBeanList != null && othersBeanList.size() > 0) {
                            for (NewsBean.OthersBean othersBean : othersBeanList) {
                                String thumbnail = othersBean.getThumbnail();
                                String name = othersBean.getName();
                                String description = othersBean.getDescription();
                                Log.i(TAG, "thumbnail:---->" + thumbnail);
                                Log.i(TAG, "name:---->" + name);
                                Log.i(TAG, "description:---->" + description);

                                //构造Adapter所需的数据源
                                SimpleNewsBean simpleNewsBean = new SimpleNewsBean();
                                simpleNewsBean.thumbnail.set(thumbnail);
                                simpleNewsBean.name.set(name);
                                simpleNewsBean.description.set(description);
                                signInList.add(simpleNewsBean);

                                if (page > 1) {
                                    //这个接口暂时没有分页的数据，这里为了模拟分页，通过取第1条数据作为分页的数据
                                    break;
                                }
                            }
                        }
                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                        Log.i(TAG, "onStart: ");
                        loadListener.loadStart();
                    }

                    @Override
                    public void onError(@NonNull Throwable throwable) {
                        Log.i(TAG, "onError: " + throwable.getMessage());
                        loadListener.loadFailure(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete: ");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadListener.loadSuccess(signInList);
                                loadListener.loadComplete();
                            }
                        }, 2000);
                    }
                });*/
    }
}
