package com.point.april.bean;

public class LoginCallback {
	private long createTime;
	private String refreshtoken;
	private int status;
	private String thirdRefreshToken;
	private String thirdToken;
	private String token;
	private String msg;
	//private MieUser user;
	private String user_id;
	private String sso_token;
	
	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getRefreshtoken() {
		return refreshtoken;
	}

	public void setRefreshtoken(String refreshtoken) {
		this.refreshtoken = refreshtoken;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getThirdRefreshToken() {
		return thirdRefreshToken;
	}

	public void setThirdRefreshToken(String thirdRefreshToken) {
		this.thirdRefreshToken = thirdRefreshToken;
	}

	public String getThirdToken() {
		return thirdToken;
	}

	public void setThirdToken(String thirdToken) {
		this.thirdToken = thirdToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	//public MieUser getUser() {
	//	return user;
	//}

	//public void setUser(MieUser user) {
	//	this.user = user;
	//}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getSso_token() {
		return sso_token;
	}

	public void setSso_token(String sso_token) {
		this.sso_token = sso_token;
	}
}
