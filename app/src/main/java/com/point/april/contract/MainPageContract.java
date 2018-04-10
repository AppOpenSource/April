package com.point.april.contract;

import com.point.april.bean.Inform;
import com.point.april.bean.request.InformRequest;
import com.point.april.model.IBaseModel;
import com.point.april.model.IBaseRespone;
import com.point.april.presenter.IBasePresenter;
import com.point.april.ui.callback.IBaseView;

import java.util.List;

public interface MainPageContract {

	public interface View extends IBaseView<Presenter> {
		 void showCommunityInformsError();
		 void showCommunityInforms(List<Inform> list);
	}

	public interface Presenter extends IBasePresenter {
		void getMoreInforms(InformRequest req);
		void getNewInforms(InformRequest req);
	}
	
	public interface Model extends IBaseModel {
		void getNewInforms(InformRequest req, Respone listener);
		void getMoreInforms(InformRequest req, Respone listener);
	}
	
	public interface Respone extends IBaseRespone {
		void onGetInformsFailed();
		void onGetInformsSuccess(List<Inform> list);
	}
}
