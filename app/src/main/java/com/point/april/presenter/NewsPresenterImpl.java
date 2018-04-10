package com.point.april.presenter;

import com.point.april.bean.ErrorInfo;
import com.point.april.bean.News;
import com.point.april.contract.NewsContract;
import com.point.april.global.GlobalParams;
import com.point.april.model.NewsModelImpl;
import com.point.april.global.GlobalConstant;

import java.util.List;

public class NewsPresenterImpl extends BasePresenterImpl implements
        NewsContract.Presenter, NewsContract.GetNewsRespone,
		NewsContract.OnDelRespone {

	private NewsContract.View mView;
	private NewsContract.Model mModel;
	
	public NewsPresenterImpl(NewsContract.View view) {
		mView = view;
		mModel = new NewsModelImpl();
	}

	@Override
	public void getLatestNews() {
		if (GlobalParams.getDebug()) {
			mModel.mockLatestNews(this);
		} else {
			mModel.getLatestNews(this);
		}
	}

	@Override
	public void getMoreNews() {
		if (GlobalParams.getDebug()) {
			mModel.mockLatestNews(this);
		} else {
			mModel.getMoreNews(this);
		}
	}

	@Override
	public void getNewsCache() {
		mModel.getNewsCache(GlobalConstant.URL_GET_MORE_NEWS, this);
	}

	@Override
	public void onGetFailed(ErrorInfo info) {
		mView.showError(info);
	}
	
	@Override
	public void onGetSuccess(List<News> list) {
		mView.showNewsRecord(list);
	}

	@Override
	public void delRecord(int recordId, int type) {
		mModel.delRecord(recordId, type, this);
	}

	@Override
	public void onDelFailed(ErrorInfo info) {
		mView.showDelError(info);
	}

	@Override
	public void onDelSuccess(int recordId) {
		mView.showDelSuccess(recordId);
	}

}
