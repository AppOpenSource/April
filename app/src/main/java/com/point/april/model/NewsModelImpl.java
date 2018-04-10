package com.point.april.model;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.point.april.bean.ErrorInfo;
import com.point.april.bean.News;
import com.point.april.bean.NewsRespone;
import com.point.april.common.log.LogManager;
import com.point.april.contract.NewsContract;
import com.point.april.global.GlobalConstant;
import com.point.april.global.GlobalParams;
import com.point.april.network.NetworkManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsModelImpl extends BaseModelImpl implements NewsContract.Model {

	private static String TAG = NewsModelImpl.class.getSimpleName();
	private Gson mGson;
	private int mRecordId;
	public NewsModelImpl() {
		mGson = new Gson();
	}

	@Override
	public void getLatestNews(final NewsContract.GetNewsRespone callback) {
		LogManager.d(TAG, "getLatestNews()");
		Map<Object, Object> map = new HashMap<Object, Object>();
		commonParam(map);
		map.put(GlobalConstant.KEY_NEWS_ID, GlobalParams.getNews_id());

		JSONObject jsonParams = new JSONObject(map);
		LogManager.d(TAG, "getLatestNews, jsonParams: "+jsonParams.toString());
		LogManager.d(TAG, "GlobalConstant.URL_GET_LATEST_NEWS\n"+GlobalConstant.URL_GET_LATEST_NEWS);

		JsonObjectRequest request = postRequest(GlobalConstant.URL_GET_LATEST_NEWS, jsonParams, callback);
		request.setTag(request.getTag());
		request.setShouldCache(true);
		RequestQueue requestQueue = NetworkManager.getInstace().getRequestQueue();
		requestQueue.add(request);
	}

	@Override
	public void getMoreNews(final NewsContract.GetNewsRespone callback) {
		LogManager.d(TAG, "getMoreNews()");
		Map<Object, Object> map = new HashMap<Object, Object>();
		commonParam(map);
		map.put(GlobalConstant.KEY_NEWS_ID, GlobalParams.getNews_id());
		
		JSONObject jsonParams = new JSONObject(map);
		LogManager.d(TAG, "getMoreNews, jsonParams: "+jsonParams.toString());
		LogManager.d(TAG, "CommonConstant.URL_GET_MORE_NEWS\n"+
				GlobalConstant.URL_GET_MORE_NEWS);

		JsonObjectRequest request = postRequest(GlobalConstant.URL_GET_MORE_NEWS, jsonParams, callback);
		request.setTag(request.getTag());
		request.setShouldCache(true);
		RequestQueue requestQueue = NetworkManager.getInstace().getRequestQueue();
		requestQueue.add(request);
	}

	@Override
	public void getNewsCache(final String url, final NewsContract.GetNewsRespone callback) {
		LogManager.d(TAG, "getNewsCache()");
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
		parseNews(response, callback);
	}

	private void parseNews(JSONObject response, final NewsContract.GetNewsRespone callback) {
		List<News> list = new ArrayList<News>();
		NewsRespone res = getGson().fromJson(response.toString(), NewsRespone.class);
		if (res != null) list = res.getNews();

		if (GlobalConstant.RES_TAG_OK.equalsIgnoreCase(res.getSuccess_ornot())) {
			callback.onGetSuccess(list);
		} else {
			callback.onGetFailed(new ErrorInfo());
		}
	}

	public List<News> parseJson(JSONObject response) {
		LogManager.d(TAG, "parseJson\n"+response.toString());
		List<News> list = new ArrayList<News>();
		NewsRespone res = mGson.fromJson(response.toString(), NewsRespone.class);
		if (res != null) list = res.getNews();
		return list;
	}

	@Override
	public void mockLatestNews(NewsContract.GetNewsRespone listener) {
		// MockData.getInstance().mockLatestNews();
		List<News> list = null;
		listener.onGetSuccess(list);
	}

	@Override
	public void delRecord(final int recordId, final int type, final NewsContract.OnDelRespone listener) {
		LogManager.d(TAG, "delRecord");
		mRecordId = recordId;
		Map<Object, Object> map = new HashMap<Object, Object>();
		commonParam(map);
		map.put(GlobalConstant.KEY_ID, recordId);
		map.put(GlobalConstant.KEY_TYPE, type);
		
		JSONObject jsonParams = new JSONObject(map);
		LogManager.d(TAG, "delRecord, jsonParams: "+jsonParams.toString());
		LogManager.d(TAG, "CommonConstant.DEL_SERVICE_ITEM\n"+GlobalConstant.URL_DEL_SERVICE_ITEM);

		JsonObjectRequest request = postRequest(GlobalConstant.URL_DEL_SERVICE_ITEM, jsonParams, listener);
		request.setTag(request.getTag());
		request.setShouldCache(true);
		RequestQueue requestQueue = NetworkManager.getInstace().getRequestQueue();
		requestQueue.add(request);
	}

	@Override
	public void dispatchRespones(String url, JSONObject jsonObj, IBaseRespone respone) {
		super.dispatchRespones(url, jsonObj, respone);
		final NewsContract.GetNewsRespone callback = ((NewsContract.GetNewsRespone) respone);
		if (GlobalConstant.URL_GET_MORE_NEWS.equalsIgnoreCase(url)) {
			parseNews(jsonObj, callback);
		} else if (GlobalConstant.URL_GET_LATEST_NEWS.equalsIgnoreCase(url)) {
			parseNews(jsonObj, callback);
		} else if (GlobalConstant.URL_DEL_SERVICE_ITEM.equalsIgnoreCase(url)) {
			handleRes(((NewsContract.OnDelRespone) respone), jsonObj);
		}
	}

	@Override
	public void dispatchError(String url, String error, IBaseRespone respone) {
		super.dispatchError(url, error, respone);
		final NewsContract.GetNewsRespone callback = ((NewsContract.GetNewsRespone) respone);
		if (GlobalConstant.URL_GET_MORE_NEWS.equalsIgnoreCase(url)) {
			getNewsCache(url, callback);
		} else if (GlobalConstant.URL_GET_LATEST_NEWS.equalsIgnoreCase(url)) {
			getNewsCache(url, callback);
		} else if (GlobalConstant.URL_DEL_SERVICE_ITEM.equalsIgnoreCase(url)) {
			ErrorInfo info = new ErrorInfo();
			((NewsContract.OnDelRespone) respone).onDelFailed(info);
		}
	}

	private void handleRes(NewsContract.OnDelRespone listener, JSONObject response) {
		if (response != null) {
			try {
				String res = response.getString("success_ornot");
				if (res != null && res.equalsIgnoreCase("OK")) {
					listener.onDelSuccess(mRecordId);
				} else {
					ErrorInfo info = new ErrorInfo();
					info.setErrorCode(GlobalConstant.FAILED);
					info.setErrorContent("FAILED");
					listener.onDelFailed(info);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

}
