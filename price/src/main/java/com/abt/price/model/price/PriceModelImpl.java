package com.abt.price.model.price;

import android.os.Handler;
import android.util.Log;

import com.abt.basic.arch.mvvm.view.load.BaseLoadListener;
import com.abt.price.bean.price.PriceBean;
import com.abt.price.bean.price.SimplePriceBean;
import com.abt.price.api.retrofitHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @描述： @PriceModelImpl
 * @作者： @黄卫旗
 * @创建时间： @20/05/2018
 */
public class PriceModelImpl implements IPriceModel {

    private static final String TAG = "PriceModelImpl";
    List<SimplePriceBean> simplePriceBeanList = new ArrayList<>();

    @Override
    public void loadPriceData(final int page, final BaseLoadListener<SimplePriceBean> loadListener) {
        retrofitHelper.getPriceData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<PriceBean>() {
                    @Override
                    public void onNext(@NonNull PriceBean priceBean) {
                        Log.i(TAG, "onNext: priceBean"+priceBean);
                        List<PriceBean.OthersBean> othersBeanList = priceBean.getOthers();
                        //simplePriceBeanList.clear();
                        if (othersBeanList != null && othersBeanList.size() > 0) {
                            for (PriceBean.OthersBean othersBean : othersBeanList) {
                                String title = othersBean.getTitle();
                                String description = othersBean.getDescription();
                                String thumbnail = othersBean.getThumbnail();
                                Boolean done = othersBean.getDone();
                                Log.i(TAG, "title:---->" + title);
                                Log.i(TAG, "description:---->" + description);

                                //构造Adapter所需的数据源
                                SimplePriceBean simplePriceBean = new SimplePriceBean();
                                simplePriceBean.done.set(done);
                                simplePriceBean.title.set(title);
                                simplePriceBean.thumbnail.set(thumbnail);
                                simplePriceBean.description.set(description);
                                simplePriceBeanList.add(simplePriceBean);

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
                                loadListener.loadSuccess(simplePriceBeanList);
                                loadListener.loadComplete();
                            }
                        }, 2000);
                    }
                });
    }
}
