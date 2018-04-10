package com.point.april.ui.activity.personal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.point.april.R;
import com.point.april.bean.LoginCallback;
import com.point.april.contract.PersonalContract;
import com.point.april.data.PreferencesUtil;
import com.point.april.global.GlobalConstant;
import com.point.april.ui.activity.AprilActivity;
import com.point.base.control.log.LogManager;
import com.point.base.ui.BaseActivity;

import java.util.regex.Pattern;

/**
 * 登录页面
 */
public class LoginActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener, PersonalContract.LoginView {

	private static final String TAG = LoginActivity.class.getSimpleName();
	private Button mBtLogin, mBtForgetPasswd;
	private LinearLayout mGoBack;
	private TextView mTitle;
	private TextView mTvRemind;
	private Button mBtTest;
	private RelativeLayout mRlPassword;
	private EditText mEtUsername, mEtPassword;
	private boolean mIsLegitimate = false;

	public final static int MSG_HIDE_USER_LIST = 1;
	public final static int MSG_DELETE_USER = 2;
	private final static int MSG_PASSWORD_INPUT_CHANGED = 3;
	public static  boolean isLogin = false;
	//private List<UserInfo> mUserList = new ArrayList<UserInfo>();
	private PersonalContract.LoginPresenter mPresenter;

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
				case MSG_DELETE_USER:
					//mUserListAdapter.notifyDataSetChanged();
					break;
				case MSG_PASSWORD_INPUT_CHANGED:
					mEtPassword.requestFocus();
					break;
				default:
					break;
			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//DeviceDataInterface.getmInstance().addDeviceCallBackListener(this);
		LogManager.d(TAG, "onCreate addDeviceCallBackListener");
		//DeviceDataInterface.getmInstance().addSDKLoginCallBackListener(this);
		LogManager.d(TAG, "onCreate addSDKLoginCallBackListener");

		String str = PreferencesUtil.getString(LoginActivity.this, GlobalConstant.SHARE_KEY_IS_LOGIN);
		if (GlobalConstant.SHARE_VALUE_IS_LOGIN.equalsIgnoreCase(str)) {
			Intent intent = new Intent(this, AprilActivity.class);
			startActivity(intent);
			finish();
		}
	}

	@Override
	protected void initVariables() {
		super.initVariables();
		//mUserDatabase = new UserDatabase(this);
		//mPresenter = new LoginPresenterImpl(this);
		LogManager.d(TAG, "initVariables");
	}

	@Override
	protected int getLayoutResource() {
		return R.layout.activity_login;
	}

	@Override
	protected void initView() {
		LogManager.d(TAG, "initView");
		mTitle = (TextView) findViewById(R.id.title_content);
		mTitle.setText("登录");

		mGoBack = (LinearLayout) findViewById(R.id.go_back);
		mGoBack.setOnClickListener(this);

		mBtLogin = (Button) findViewById(R.id.bt_login);
		mBtLogin.setOnClickListener(this);
		mBtLogin.setTextColor(getResources().getColor(R.color.unable_black_button));
		//mBtLogin.setEnabled(false);
		mBtForgetPasswd = (Button) findViewById(R.id.bt_forget_password);
		mBtTest = (Button) findViewById(R.id.self_test);
		mBtTest.setOnClickListener(this);

		//下拉的信息
		/*mBtUserlist = (ImageButton) findViewById(R.id.bt_other_user);
		mRlPassword = (RelativeLayout) findViewById(R.id.rl_password);*/

		//important:输入的账号和密码
		mEtUsername = (EditText) findViewById(R.id.et_user_name);
		mEtUsername.setText("18610537422");
		mEtUsername.addTextChangedListener(new UserNameListener());
		mEtPassword = (EditText) findViewById(R.id.et_password);
		mTvRemind = (TextView) findViewById(R.id.tv_remind);
		mEtPassword.addTextChangedListener(new UserNameListener());
		mEtPassword.setText("1234abcd");

		mGoBack.setOnClickListener(this);
		mBtForgetPasswd.setOnClickListener(this);
		mEtUsername.setOnClickListener(this);
		mEtPassword.setOnClickListener(this);
	}

	@Override
	public void setPresenter(PersonalContract.Presenter presenter) {

	}

	public void onLoginFailed(String errorMsg) {
		isLogin=false;
	}

	public void onLoginSuccess(LoginCallback login) {
		//UserCommand.init(this.getApplicationContext());
		LogManager.d(TAG, "onLoginSuccess");

		String username = "";//login.getUser().getUsername();
		//String username = "18610240057";
		LogManager.d(TAG, "deviceDataInterfaceInit username : "+username);

		String token = login.getSso_token();
		//String token = "5f15f57a93fa38e0541bf846af9e6201";
		LogManager.d(TAG, "deviceDataInterfaceInit token : "+token);

		//DeviceDataInterface.getmInstance().deviceDataInterfaceInit(this.getApplication(),
		//		username, token, UserManager.Phone_Type);//Phone_Type会互踢 || TV_Type不会互踢 // TODO
	}

	private class UserNameListener implements TextWatcher {

		@Override
		public void afterTextChanged(Editable s) { }

		@Override
		public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
									  int arg3) { }

		@Override
		public void onTextChanged(CharSequence str, int start, int before,
								  int count) {
			String phone = mEtUsername.getText().toString().trim();
			String passwd = mEtPassword.getText().toString().trim();
			if (phone.length() == 11) {
				//mBtLogin.setTextColor(getResources().getColor(R.color.white));
				mBtLogin.setEnabled(true);
				mIsLegitimate = true;
			} else {
				//mBtLogin.setTextColor(getResources().getColor(R.color.unable_black_button));
				mIsLegitimate = false;
				mBtLogin.setEnabled(false);
			}

			if (passwd.length()>0) {
				//mTvRemind.setVisibility(View.INVISIBLE);
			}
		}
	}

	@Override
	protected void loadData() {
		LogManager.d(TAG, "initView");
		//mUserDatabase.queryAllUser();
		/*if (mUserList.size()!=0 && mUserList!=null) {
			String username = mUserList.get(0).getUsername();
			mEtUsername.setText(username);
			mEtUsername.setSelection(username.length());
		}*/
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.bt_login:
				LogManager.d(TAG, "onClick aaa");
				final String name = mEtUsername.getText().toString().trim();
				final String nickName = "黄卫旗";
				String passwd = mEtPassword.getText().toString().trim();
				if (!TextUtils.isEmpty(name) && mIsLegitimate) {
					if (!TextUtils.isEmpty(passwd)) {
						if (!isNumeric(name) || name.length() != 11) {
							mTvRemind.setVisibility(View.VISIBLE);
							mTvRemind.setText("请输入正确的手机号码");
						} else if (isNumeric(name) && name.length() == 11 && passwd.length() < 6 || passwd.length() > 20) {
							mTvRemind.setVisibility(View.VISIBLE);
							mTvRemind.setText("请输入正确的密码");
						}
						// else if (isNumeric(name)&&name.length()==11&&
						// passwd.length()>=6&&passwd.length()<=2
						// &&NetworkUtil.isNetworkAvailable(this)) {
						mTvRemind.setVisibility(View.VISIBLE);
						//mPresenter.login(name, passwd, this);
						//login(name, passwd, LoginActivity.this);
						mBtLogin.setText("登录中...");
						mBtLogin.setTextColor(getResources().getColor(R.color.white));
						mTvRemind.setVisibility(View.INVISIBLE);
						mBtLogin.postDelayed(new Runnable() {
							@Override
							public void run() {
								PreferencesUtil.write(LoginActivity.this, GlobalConstant.SHARE_KEY_IS_LOGIN,
										GlobalConstant.SHARE_VALUE_IS_LOGIN);
								PreferencesUtil.write(LoginActivity.this, GlobalConstant.SHARE_KEY_USER_NAME, name);
								PreferencesUtil.write(LoginActivity.this, GlobalConstant.SHARE_KEY_NICK_NAME, nickName);
								Intent intent = new Intent(LoginActivity.this, AprilActivity.class);
								startActivity(intent);
								finish();
							}
						}, 1*1000);
					} else if (isNumeric(name) && name.length() == 11 &&
							passwd.length() >= 6 && passwd.length() <= 20) {
						mTvRemind.setVisibility(View.VISIBLE);
						mTvRemind.setText("登录失败,请检查网络");
					}
				} else {
					mTvRemind.setVisibility(View.VISIBLE);
					mTvRemind.setText("请输入密码");
				}
				break;
			//case R.id.register:
			//Intent registerIntent = new Intent(this, RegisterActivity.class);
			//startActivity(registerIntent);
			//	break;
			//case R.id.bt_forget_password:
			//	//Intent forgetPasswdIntent = new Intent(this, ResetPasswordActivity.class);
			//startActivity(forgetPasswdIntent);
			//break;
			case R.id.go_back:
				finish();
				break;
			default:
				break;
		}
	}

	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	@Override
	public void showError() {

	}

	@Override
	public void showNameError() {
		mBtLogin.post(new Runnable() {

			@Override
			public void run() {
				mBtLogin.setText("登录");
				mTvRemind.setVisibility(View.VISIBLE);
				mTvRemind.setText("请输入正确的手机号码");
			}
		});
	}

	@Override
	public void showPasswordError() {
		mBtLogin.post(new Runnable() {
			@Override
			public void run() {
				mBtLogin.setText("登录");
				mTvRemind.setVisibility(View.VISIBLE);
				mTvRemind.setText("请输入正确的密码！");
			}
		});
	}

	@Override
	public void showLoginError(final String errorMsg) {
		Log.e(TAG, "---showLoginError : " + errorMsg);
		if(!TextUtils.isEmpty(errorMsg)){
			mBtLogin.post(new Runnable() {

				@Override
				public void run() {
					mBtLogin.setText("登录");
					mTvRemind.setVisibility(View.VISIBLE);
					mTvRemind.setText("登录失败," + errorMsg);
				}
			});
		}else{
			mBtLogin.post(new Runnable() {

				@Override
				public void run() {
					mBtLogin.setText("登录");
					mTvRemind.setVisibility(View.VISIBLE);
					mTvRemind.setText("登录失败,返回码为空");
				}
			});
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		Log.i(TAG, "userlist arg2 = " + position);
		/*UserInfo userInfo = null;
		if (mUserList.size() > position) {
			userInfo = mUserList.get(position);
			mEtUsername.setText(userInfo.getUsername());
			mEtUsername.setSelection(userInfo.getUsername().length());
		}*/
	}

	@Override
	public void showLoginSuccess(LoginCallback login) {
		Log.d(TAG, "---showLoginSuccess登录成功: " + login.toString());
		handlerLoginSuccess();
	}

	public void handlerLoginSuccess() {
		LogManager.d(TAG, "handlerLoginSuccess ...");
		//TODO
		/*if (login != null) {
			Log.d(TAG, "login pic: "+login.getUser().getHeadpic());
		}*/
		LogManager.d(TAG, "---else showLoginSuccess ");
		String name = mEtUsername.getText().toString();
		//OldManApp.mIsLogin = true;
		PreferencesUtil.write(this,GlobalConstant.SHARE_KEY_IS_LOGIN, GlobalConstant.SHARE_VALUE_IS_LOGIN);
		PreferencesUtil.write(this,GlobalConstant.SHARE_VALUE_USER_NAME, name);
		Intent intent = new Intent();
		setResult(2000, intent);
		finish();
		//}
	}

	public void login(String name, String passwd, final PersonalContract.LoginView listener) {
		//name = "18610537422";
		//passwd = "1234abcd";
		LogManager.d(TAG, "login name :"+name);
		LogManager.d(TAG, "login passwd :"+passwd);
		/*MieCommand.getmInstance(LoginActivity.this.getApplicationContext()).login(name, passwd, new LoginListener() {

			@Override
			public void getLoginToken(String original, String name, String ssoToken) {
				Log.d(TAG, "getLoginToken, original: "+original);
				Log.d(TAG, "getLoginToken, name: "+name);
				Log.d(TAG, "getLoginToken, ssotoken: "+ssoToken);
				LogManager.d(TAG, "original: "+original);
				LogManager.d(TAG, "name: "+name);
				LogManager.d(TAG, "ssotoken: "+ssoToken);
				PreferencesUtil.write(LoginActivity.this, GlobalConstant.SHARE_KEY_USER_NAME, name);
				PreferencesUtil.write(LoginActivity.this, GlobalConstant.SHARE_KEY_SSO_TOKEN, ssoToken);

				LoginCallback login = new LoginCallback();
				MieUser user = new MieUser();
				user.setUsername(name);
				login.setSso_token(ssoToken);
				login.setUser(user);
				listener.onLoginSuccess(login);
			}

			@Override
			public void error(String arg0) {
				LogManager.e(TAG, "---error: "+arg0);
				listener.onLoginFailed(arg0);
			}
		});*/
	}
}
