package com.point.april.presenter;

import android.content.Context;

import com.google.gson.Gson;
import com.point.april.api.TxRequest;
import com.point.april.bean.TxWeixinResponse;
import com.point.april.global.GlobalConstant;
import com.point.april.ui.iView.IWeixinFragment;
import com.point.april.util.CacheUtil;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 蔡小木 on 2016/4/22 0022.
 */
public class WeiXinPresenterImpl extends BasePresenterImpl implements IWeixinPresenter {

    private CacheUtil mCacheUtil;
    private IWeixinFragment mWeixinFragment;
    private Gson mGson = new Gson();

    public WeiXinPresenterImpl(IWeixinFragment weixinFragment, Context context) {
        if (weixinFragment==null)
            throw new IllegalArgumentException("weixinFragment must not be null");
        this.mWeixinFragment = weixinFragment;
        mCacheUtil = CacheUtil.get(context);
    }

    @Override
    public void getWeixinNews(final int page) {
        mWeixinFragment.showProgressDialog();
        //Subscription subscription = TxRequest.getTxApi().getWeixin(page).subscribeOn(Schedulers.io())
        Subscription subscription = TxRequest.getTxApi().getWeixin(page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TxWeixinResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        mWeixinFragment.hidProgressDialog();
                        mWeixinFragment.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(TxWeixinResponse txWeixinResponse) {
                        mWeixinFragment.hidProgressDialog();
                        if (txWeixinResponse.getCode() == 200) {
                            mWeixinFragment.updateList(txWeixinResponse.getNewslist());
                            mCacheUtil.put(GlobalConstant.WEIXIN + page, mGson.toJson(txWeixinResponse));
                        } else {
                            mWeixinFragment.showError("服务器内部错误！");
                        }
                    }
                });
        addSubscription(subscription);
    }

    @Override
    public void getWeixinNewsFromCache(int page) {
        if (mCacheUtil.getAsJSONObject(GlobalConstant.WEIXIN + page) != null) {
            TxWeixinResponse txWeixinResponse = mGson.fromJson(mCacheUtil.getAsJSONObject(GlobalConstant.WEIXIN + page).toString(), TxWeixinResponse.class);
            mWeixinFragment.updateList(txWeixinResponse.getNewslist());
        }
    }
}
