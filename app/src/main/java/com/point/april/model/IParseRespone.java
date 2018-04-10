package com.point.april.model;

import org.json.JSONObject;

public interface IParseRespone extends IBaseRespone {
	
	void dispatchRespones(String url, JSONObject jsonObj, IBaseRespone callback);
	void dispatchError(String url, String error, IBaseRespone callback);
	
}
