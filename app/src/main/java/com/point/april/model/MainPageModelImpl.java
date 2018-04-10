package com.point.april.model;

import com.android.volley.RequestQueue;
import com.android.volley.ext.tools.HttpTools;
import com.android.volley.toolbox.JsonObjectRequest;
import com.point.april.bean.Inform;
import com.point.april.bean.Informs;
import com.point.april.bean.request.InformRequest;
import com.point.april.common.log.LogManager;
import com.point.april.contract.MainPageContract;
import com.point.april.global.GlobalParams;
import com.point.april.global.GlobalConstant;
import com.point.april.network.NetworkManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPageModelImpl extends BaseModelImpl implements MainPageContract.Model {

	private static final String TAG = MainPageModelImpl.class.getSimpleName();

	@Override
	public void getMoreInforms(InformRequest req, final MainPageContract.Respone callback) {
		LogManager.d(TAG, "getMoreInforms");
		String url = GlobalConstant.URL_GET_MORE_INFORM;
		Map<Object, Object> map = new HashMap<Object, Object>();
		commonParam(map);
		JSONObject params = new JSONObject(map);
		
		JsonObjectRequest request = postRequest(url, params, callback);
		request.setTag(req.getTag());
		request.setShouldCache(true);
		RequestQueue requestQueue = HttpTools.getHttpRequestQueue();
		requestQueue.add(request);
	}
	
	@Override
	public void getNewInforms(InformRequest req, final MainPageContract.Respone callback) {
		LogManager.d(TAG, "getNewInforms");
		String url = GlobalConstant.URL_GET_NEW_INFORM;
		Map<Object, Object> map = new HashMap<Object, Object>();
		commonParam(map);
		map.put(GlobalConstant.KEY_NOTIFY_ID, GlobalParams.getNotify_id());
		JSONObject params = new JSONObject(map);
		
		JsonObjectRequest request = postRequest(url, params, callback);
		request.setTag(req.getTag());
		request.setShouldCache(true);
		RequestQueue requestQueue = NetworkManager.getInstace().getRequestQueue();
		requestQueue.add(request);
	}
	
	@Override
	public void dispatchRespones(String url, JSONObject jsonObj,
			IBaseRespone respone) {
		super.dispatchRespones(url, jsonObj, respone);
		final MainPageContract.Respone callback = ((MainPageContract.Respone) respone);
		if (GlobalConstant.URL_GET_MORE_INFORM.equalsIgnoreCase(url)) {
			parseInfroms(jsonObj, callback);
		} else if (GlobalConstant.URL_GET_NEW_INFORM.equalsIgnoreCase(url)) {
			parseInfroms(jsonObj, callback);
		} /*else if (GlobalConstant.URL_ADD_SUPPORT.equalsIgnoreCase(url)) {
			parseAddSupport(jsonObj, callback);
		} else if (GlobalConstant.URL_GET_AIR_QUALITY.equalsIgnoreCase(url)) {
			parseAirQuality(jsonObj, callback);
		} else if (GlobalConstant.URL_GET_TOOL_LIST.equalsIgnoreCase(url)) {
			parseTools(jsonObj, callback);
		}*/
	}
	
	@Override
	public void dispatchError(String url, String error, IBaseRespone respone) {
		super.dispatchError(url, error, respone);
		final MainPageContract.Respone callback = ((MainPageContract.Respone) respone);
		if (GlobalConstant.URL_GET_MORE_INFORM.equalsIgnoreCase(url)) {
			callback.onGetInformsFailed();
		} else if (GlobalConstant.URL_GET_NEW_INFORM.equalsIgnoreCase(url)) {
			getNewInformsCache(callback);
		}/* else if (CommonConstant.URL_ADD_SUPPORT.equalsIgnoreCase(url)) {
			callback.onAddFailed();
		} else if (CommonConstant.URL_GET_AIR_QUALITY.equalsIgnoreCase(url)) {
			callback.onGetAirFailed();
		} else if (CommonConstant.URL_GET_TOOL_LIST.equalsIgnoreCase(url)) {
			callback.onGetToolFailed();
		}*/
	}
	
	private void parseInfroms(JSONObject response, final MainPageContract.Respone callback) {
		List<Inform> list = new ArrayList<Inform>();
		Informs res = getGson().fromJson(response.toString(), Informs.class);
		if (GlobalConstant.RES_TAG_OK.equalsIgnoreCase(res.getSuccess_ornot())) {
			list = res.getInforms();
			callback.onGetInformsSuccess(list);
		} else {
			callback.onGetInformsFailed();
		}
	}
	
	private void getNewInformsCache(final MainPageContract.Respone callback) {
		LogManager.d(TAG, "getNewInformsCache");
		String url = GlobalConstant.URL_GET_NEW_INFORM;
		RequestQueue requestQueue = NetworkManager.getInstace().getRequestQueue();
		LogManager.d(TAG, "requestQueue.getCache(): "+requestQueue.getCache());
		if (null == requestQueue.getCache()) return;
		if (null == requestQueue.getCache().get(url)) return;
		LogManager.d(TAG, "requestQueue.cacheUrl(): "+requestQueue.getCache().get(url));
		
		byte[] data = requestQueue.getCache().get(url).data;
		String cacheRes = new String(data);
		LogManager.d(TAG, "cacheRes: "+cacheRes);
		JSONObject response = null;
		try {
			response = new JSONObject(cacheRes);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		parseInfroms(response, callback);
	}

}
