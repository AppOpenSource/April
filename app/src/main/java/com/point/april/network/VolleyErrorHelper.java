package com.point.april.network;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

public class VolleyErrorHelper {
	/**
	 * 给用户返回合适的错误信息
	 */
	public static String getMessage(Object error) {
		if (error instanceof TimeoutError) {
			return "generic_server_down";
		}
		else if (isServerProblem(error)) {
			return handleServerError(error);
		}
		else if (isNetworkProblem(error)) {
			return "no_internet";
		}
		return "generic_error";
	}
	
	/**
	 * 判断是否是网络错误
	 */
	private static boolean isNetworkProblem(Object error) {
		return (error instanceof NetworkError) || (error instanceof NoConnectionError);
	}
	
	/**
	 * 判断是否是服务器错误
	 */
	private static boolean isServerProblem(Object error) {
		return (error instanceof ServerError) || (error instanceof AuthFailureError);
	}
	
	/**
	 * 处理服务器错误
	 */
	private static String handleServerError(Object err) {
		VolleyError error = (VolleyError) err;
		NetworkResponse response = error.networkResponse;
		if (response != null) {
			switch (response.statusCode) {
			case 404:
			case 422:
			case 401:
				try {
					// server might return error like this { "error": "Some error occured" }
					// Use "Gson" to parse the result
					HashMap<String, String> result = new Gson().fromJson(new String(response.data),
							new TypeToken<Map<String, String>>() { }.getType());
					if (result != null && result.containsKey("error")) {
						return result.get("error");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				// invalid request
				return error.getMessage();
			default:
				return "generic_server_down";
			}
		}
		return "generic_error";
	}
}
