package com.abt.price.model.zhihu;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.api.ApiFactory;
import com.abt.price.api.ZhihuApi;
import com.abt.price.bean.zhihu.News;
import com.abt.price.bean.zhihu.NewsTimeLine;
import com.abt.price.bean.zhihu.Stories;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @描述： @ZhihuModelImpl
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class ZhihuModelImpl implements IZhihuModel {

    private static final String TAG = "ZhihuModelImpl";
    private String time = null;
    private List<Stories> timeLineList = new ArrayList<>();
    public static final ZhihuApi zhihuApi = ApiFactory.getZhihuApiSingleton();

    @Override
    public void getLatestNews(final int page, final BaseLoadListener<Stories> loadListener) {
        zhihuApi.getLatestNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsTimeLine>() {

                    @Override
                    public void onNext(@NonNull NewsTimeLine bean) {
                        Log.i(TAG, "onNext: ");
                        time = bean.getDate();
                        timeLineList.clear();
                        timeLineList = bean.getStories();
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
                                loadListener.loadSuccess(timeLineList);
                                loadListener.loadComplete();
                            }
                        });
                    }
                });
    }

    @Override
    public void getBeforeNews(int page, BaseLoadListener<Stories> loadListener) {
        zhihuApi.getBeforetNews(time)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsTimeLine>() {

                    @Override
                    public void onNext(@NonNull NewsTimeLine bean) {
                        Log.i(TAG, "onNext: ");
                        time = bean.getDate();
                        timeLineList.clear();
                        timeLineList = bean.getStories();
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
                                loadListener.loadSuccess(timeLineList);
                                loadListener.loadComplete();
                            }
                        });
                    }
                });
    }

    @Override
    public void getDetailNews(String id, BaseLoadListener<News> loadListener) {
        List<News> newsList = new ArrayList<>();
        zhihuApi.getDetailNews(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<News>() {

                    @Override
                    public void onNext(@NonNull News bean) {
                        Log.i(TAG, "onNext: ");
                        newsList.add(bean);
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
                                loadListener.loadSuccess(newsList);
                                loadListener.loadComplete();
                            }
                        }, 0);
                    }
                });
    }

}
