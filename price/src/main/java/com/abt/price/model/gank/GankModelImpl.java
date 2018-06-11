package com.abt.price.model.gank;

import android.os.Handler;
import android.util.Log;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.api.ApiFactory;
import com.abt.price.api.GankApi;
import com.abt.price.bean.gank.Meizhi;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @描述： @ZhihuModelImpl
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class GankModelImpl implements IGankModel {

    private static final String TAG = "ZhihuModelImpl";
    private List<Meizhi> gankList = new ArrayList<>();
    public static final GankApi gankApi = ApiFactory.getGankApiSingleton();

    @Override
    public void loadMeizhiData(final int page, final BaseLoadListener<Meizhi> loadListener) {
        gankApi.getMeizhiData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Meizhi>() {
                    @Override
                    public void onNext(@NonNull Meizhi meizhi) {
                        gankList.add(meizhi);
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
                        new Handler().post(new Runnable() {
                            @Override
                            public void run() {
                                loadListener.loadSuccess(gankList);
                                loadListener.loadComplete();
                            }
                        });
                    }
                });
    }
}
