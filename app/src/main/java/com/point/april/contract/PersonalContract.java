package com.point.april.contract;

import android.content.Context;

import com.point.april.bean.LoginCallback;
import com.point.april.model.IBaseModel;
import com.point.april.model.IBaseRespone;
import com.point.base.model.control.BaseView;

public interface PersonalContract {

	public interface LoginView extends BaseView<Presenter> {
		void showError();
		void showNameError();
		void showPasswordError();
		void showLoginError(String errorMsg);
		void showLoginSuccess(LoginCallback login);
	}

	public interface OrderView extends BaseView<Presenter> {
		void showError();
		//void showOrders(List<OrderInfo> list);
	}

	public interface View extends BaseView<Presenter> {
		void showError();
		void showProsonalImg(String path);

		void showUpdateNickNameError();
		//void showUpdateNickNameSuccess(LoginCallback call);

		void showHeadPicUploadError();
		void showHeadPicUploadSuccess(String url);

		//void showMsgs(List<Msg> list);
		//void showWifiDevices(List<WiFiDevice> list);
	}

	public interface Presenter {
		void getVerifyCode(String phone);
		void resetPasswd(String phone, String safeCode, String newPasswd);
		void notifyMieChange();

		void userAuth();
		void getPersonInfo(String name, String token);
		//void updateHeadPic(Context context, HeadPicParam param);
		//void updateNickName(LoginCallback call);

		void getMsg(String phone);
		void getOrder();
		void getWifiDevices();
		void login(String name, String passwd, Context context);
	}

	public interface LoginPresenter {
		void login(String name, String passwd, Context context);
	}

	public interface Model extends IBaseModel {
		void getPersonalInfo(String reqInfo, Respone listener);
		void mockPersonalInfo(String reqInfo, Respone listener);

		void userAuth(String reqInfo, Respone listener);
		void mockUserAuth(String reqInfo, Respone listener);

		//void updateHeadPic(Context context, HeadPicParam param, Listener<String> responListener,
		//				   ErrorListener errorListener);
		//void updateHeadPic(Context context, HeadPicParam param, OnOptListener listener);
		//void updateNickName(LoginCallback call, OnOptListener listener);

		void getVerifyCode(String phone, final Respone listener);
		void resetPasswd(String phone, String safeCode, String newPasswd, final Respone listener);
		void notifyMieChange(final Respone listener);
	}

	public interface Respone extends IBaseRespone {
		void onGetFailed();
		void onGetSuccess(String path);

		void onAuthFailed();
		void onAuthSuccess();

		void onUploadFailed();
		void onUploadSuccess();

		void onNameChangeFailed();
		//void onNameChangeSuccess(LoginCallback call);

		void onGetCodeFailed();
		void onGetCodeSuccess();

		void onResetFailed(String error, int status);
		void onResetSuccess(String record);

		void onNotifyChangeFailed();
		void onNotifyChangeSuccess(String user_id);
	}
}
