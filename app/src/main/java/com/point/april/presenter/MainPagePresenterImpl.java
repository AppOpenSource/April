package com.point.april.presenter;

import android.content.Context;

import com.point.april.bean.Inform;
import com.point.april.bean.request.InformRequest;
import com.point.april.common.log.LogManager;
import com.point.april.contract.MainPageContract;
import com.point.april.global.GlobalParams;
import com.point.april.model.MainPageModelImpl;

import java.util.List;

public class MainPagePresenterImpl extends BasePresenterImpl implements
		MainPageContract.Presenter, MainPageContract.Respone {

	private static final String TAG = MainPagePresenterImpl.class.getSimpleName();
	private MainPageContract.View mView;
	private MainPageContract.Model mModel;
	private Context mContext;
	
	public MainPagePresenterImpl(MainPageContract.View view, Context context) {
		mContext = context;
		mView = view;
		mModel = new MainPageModelImpl();
	}

	@Override
	public void getNewInforms(InformRequest req) {
		LogManager.d(TAG, "getNewInforms: "+req.toString());
		mModel.getNewInforms(req, this);
	}

	@Override
	public void getMoreInforms(InformRequest req) {
		if (GlobalParams.getDebug()) {
			//mModel.mockCommunityInforms(req, this);
		} else {
			mModel.getMoreInforms(req, this);
		}
	}
	
	@Override
	public void onGetInformsFailed() {
		mView.showCommunityInformsError();
	}
	
	@Override
	public void onGetInformsSuccess(List<Inform> list) {
		mView.showCommunityInforms(list);
	}


}
