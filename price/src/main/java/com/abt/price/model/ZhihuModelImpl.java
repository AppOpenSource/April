package com.abt.price.model;

import android.os.Handler;
import android.util.Log;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.bean.SimpleZhihuBean;
import com.abt.price.bean.ZhihuBean;
import com.abt.price.http.HttpUtils;

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
public class ZhihuModelImpl implements IZhihuModel {

    private static final String TAG = "ZhihuModelImpl";
    List<SimpleZhihuBean> simpleZhihuBeanList = new ArrayList<>();

    @Override
    public void loadZhihuData(final int page, final BaseLoadListener<SimpleZhihuBean> loadListener) {
        HttpUtils.getZhihuData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ZhihuBean>() {
                    @Override
                    public void onNext(@NonNull ZhihuBean bean) {
                        Log.i(TAG, "onNext: ");
                        List<ZhihuBean.OthersBean> othersBeanList = bean.getOthers();
                        simpleZhihuBeanList.clear();
                        if (othersBeanList != null && othersBeanList.size() > 0) {
                            for (ZhihuBean.OthersBean othersBean : othersBeanList) {
                                String thumbnail = othersBean.getThumbnail();
                                String name = othersBean.getName();
                                String description = othersBean.getDescription();
                                Log.i(TAG, "thumbnail:---->" + thumbnail);
                                Log.i(TAG, "name:---->" + name);
                                Log.i(TAG, "description:---->" + description);

                                //构造Adapter所需的数据源
                                SimpleZhihuBean simpleNewsBean = new SimpleZhihuBean();
                                simpleNewsBean.thumbnail.set(thumbnail);
                                simpleNewsBean.description.set(description);
                                simpleZhihuBeanList.add(simpleNewsBean);

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
                                loadListener.loadSuccess(simpleZhihuBeanList);
                                loadListener.loadComplete();
                            }
                        }, 2000);
                    }
                });
    }
}
