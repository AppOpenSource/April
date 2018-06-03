package com.point.april.model;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.point.april.global.GlobalConstant;
import com.point.april.global.GlobalParams;
import com.point.april.network.VolleyErrorHelper;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BaseModelImpl implements IBaseModel, IParseRespone {

	private static final String TAG = BaseModelImpl.class.getSimpleName();
	private Gson mGson = null;
	
	public BaseModelImpl() {
		mGson = new Gson();
	}
	
	public Gson getGson() {
		if (mGson == null) {
			mGson = new Gson();
		}
		return mGson;
	}
	
	@Override
	public void commonParam(Map<Object, Object> map) {
		map.put(GlobalConstant.KEY_AUTH, GlobalParams.getAuth());
		map.put(GlobalConstant.KEY_MIE_TOKEN, GlobalParams.getMieToken());
		map.put(GlobalConstant.KEY_MIE_USER_ID, GlobalParams.getMieUserId());
		map.put(GlobalConstant.KEY_X_SCMS_DATE, GlobalParams.getDate());
		map.put(GlobalConstant.KEY_USER_ID, GlobalParams.getUserId());
		map.put(GlobalConstant.KEY_COMMUN_ID, GlobalParams.getCommunId());
	}
	
	public JsonObjectRequest postRequest(final String url, JSONObject jsonParams, final IBaseRespone listener) {

		JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
				url, jsonParams, new Response.Listener<JSONObject>() {
			@Override
			public void onResponse(JSONObject response) {
				dispatchRespones(url, response, listener);
			}
		}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				String str = VolleyErrorHelper.getMessage(error);
				dispatchError(url, str, listener);
			}
		}) {
			@Override
			public Map<String, String> getHeaders() {
				HashMap<String, String> headers = new HashMap<String, String>();
				headers.put("Accept", "application/json");
				headers.put("Content-Type", "application/json; charset=UTF-8");
				return headers;
			}
		};
		/**
		 * DefaultRetryPolicy ： 设置超时
		 * initialTimeoutMs : 超时时间Ms
		 * maxNumRetries : 最大重试次数
		 * backoffMultiplier : 
		 */
		request.setRetryPolicy(new DefaultRetryPolicy(15 * 1000, 1, 1.0f));
		return request;
	}

	@Override
	public void dispatchRespones(String url, JSONObject jsonObj, IBaseRespone respone) {
	}

	@Override
	public void dispatchError(String url, String error, IBaseRespone respone) {
	}

}
