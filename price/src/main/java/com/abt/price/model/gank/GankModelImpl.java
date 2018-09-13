package com.abt.price.model.gank;

import android.os.Handler;
import android.util.Log;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.api.ApiFactory;
import com.abt.price.api.GankApi;
import com.abt.price.core.bean.gank.Gank;
import com.abt.price.core.bean.gank.Meizhi;
import com.abt.price.core.bean.gank.Video;
import com.abt.price.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.Observable;
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

    private static final String TAG = "GankModelImpl";
    private List<Gank> gankList = new ArrayList<>();
    public static final GankApi gankApi = ApiFactory.getGankApiSingleton();

    @Override
    public void loadMeizhiData(final int page, final BaseLoadListener<Gank> loadListener) {
        Observable.zip(gankApi.getMeizhiData(page), gankApi.getVideoData(page), this::creatDesc)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Meizhi>() {
                    @Override
                    public void onNext(@NonNull Meizhi meizhi) {
                        gankList = meizhi.getResults();
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

    /**
     * MeiZhi = list , gankmeizhi = 福利
     * @param meizhi list
     * @param video  list
     */
    private Meizhi creatDesc(Meizhi meizhi, Video video) {
        for (Gank gankmeizhi : meizhi.getResults()) {
            gankmeizhi.desc = gankmeizhi.desc + " " +
                    getVideoDesc(gankmeizhi.getPublishedAt(), video.getResults());
        }
        return meizhi;
    }

    //匹配同一天的福利描述和视频描述
    private String getVideoDesc(Date publishedAt, List<Gank> results) {
        String videoDesc = " ";
        for (int i = 0; i < results.size(); i++) {
            Gank video = results.get(i);
            if (video.getPublishedAt() == null) video.setPublishedAt(video.getCreatedAt());
            if (DateUtils.isSameDate(publishedAt, video.getPublishedAt())) {
                videoDesc = video.getDesc();
                break;
            }
        }
        return videoDesc;
    }
}
