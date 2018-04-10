package com.point.april.contract;

import com.point.april.bean.ErrorInfo;
import com.point.april.bean.News;
import com.point.april.model.IBaseModel;
import com.point.april.model.IBaseRespone;
import com.point.april.presenter.IBasePresenter;
import com.point.april.ui.callback.IBaseView;

import java.util.List;

public interface NewsContract {

	public interface View extends IBaseView<Presenter> {
		void showError(ErrorInfo info);
		void showNewsRecord(List<News> list);

		void showDelError(ErrorInfo info);
		void showDelSuccess(int recordId);
	}

	public interface Presenter extends IBasePresenter {
		void getLatestNews();
		void getMoreNews();
		void getNewsCache();
		void delRecord(int recordId, int type);
	}

	public interface Model extends IBaseModel {
		void getLatestNews(final GetNewsRespone listener);
		void getMoreNews(final GetNewsRespone listener);
		void getNewsCache(final String url, final GetNewsRespone listener);
		void mockLatestNews(final GetNewsRespone listener);
		void delRecord(int recordId, int type, OnDelRespone listener);
	}
	
	public interface GetNewsRespone extends IBaseRespone {
		void onGetFailed(ErrorInfo info);
		void onGetSuccess(List<News> list);
	}

	public interface OnDelRespone extends IBaseRespone {
		void onDelFailed(ErrorInfo info);
		void onDelSuccess(int recordId);
	}
}
