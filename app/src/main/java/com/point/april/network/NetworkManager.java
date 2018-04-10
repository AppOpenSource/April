package com.point.april.network;

import android.app.Application;
import android.text.TextUtils;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ext.tools.HttpTools;
import com.android.volley.toolbox.Volley;
import com.point.april.common.log.LogManager;
import com.point.april.widget.imageloader.ImageLoaderManager;

public class NetworkManager {
	
	private static final String TAG = NetworkManager.class.getSimpleName();
	public static final int mOptCode = -1;		// 
	public static final int DEFAULT_OPT_CODE = 1; // DO NOTHING
	public static final int SEND_VERIFY_CODE = 2;
	public static final int SEND_REGISTER_REQ = 3;
	public static final int SEND_LOGIN_REQ = 4;
	public static final int SEND_LOGOUT_REQ = 5;
	public static final int GET_USER_INFO = 6;
	public static final int UPLOAD_HEAD_PIC = 7;
	public static final int CHANGE_NICK_NAME = 8;
	public static final int SAFE_CODE = 9;
	public static final int RESET_PASSWD = 10;
	public static final int NOTIFY_DATA_CHANGE = 11;
	
	public static final int mOptType = -1;
	public static final int DEFAULT_OPT_TYPE = 1; // HTTP NORMAL
	public static final int OPT_TYPE_VOLLEY = 2;  // VOLLEY
	public static final int OPT_TYPE_OKHTTP = 3;  // OKHTTP
	
	private static NetworkManager mManager;
	private RequestQueue mRequestQueue;
	
	public static NetworkManager getInstace() {
		if (mManager == null) {
			mManager = new NetworkManager();
		}
		return mManager;
	}
	
	public void init(Application app) {
		HttpTools.init(app.getApplicationContext());//Volley无缓存的请求队列 TODO
		mRequestQueue = Volley.newRequestQueue(app.getApplicationContext());//有缓存的请求队列
		
		//NoHttp.init(app);
	}
	
	public void setRequestQueue(RequestQueue requestQueue) {
		this.mRequestQueue = requestQueue;
	}
	
	 /**
     * @return The Volley Request queue, the queue will be created if it is null
     */
    public RequestQueue getRequestQueue() {
        // lazy initialize the request queue, the queue instance will be
        // created when it is accessed for the first time
        /*if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }*/

        return mRequestQueue;
    }

    /**
     * Adds the specified request to the global queue, if tag is specified
     * then it is used else Default TAG is used.
     * 
     * @param req
     * @param tag
     */
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);

        LogManager.d("Adding request to queue: %s", req.getUrl());

        getRequestQueue().add(req);
    }

    /**
     * Adds the specified request to the global queue using the Default TAG.
     * 
     * @param req
     * @param //tag
     */
    public <T> void addToRequestQueue(Request<T> req) {
        // set the default tag if tag is empty
        req.setTag(TAG);

        getRequestQueue().add(req);
    }

    /**
     * Cancels all pending requests by the specified TAG, it is important
     * to specify a TAG so that the pending/ongoing requests can be cancelled.
     * 
     * @param tag
     */
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

	/**
	 * 为特定ImageView加载指定url的网络图片
	 * @param view
	 * @param url
	 */
	public void loadImg(ImageView view, String url) {
		// UniversalImageLoader.loadImg(url, view);

		ImageLoaderManager.getInstance().loadImg(url, view);
	}

	public void cancelRequest(String tag) {
		LogManager.d(TAG, "cancleReq: "+tag);
		//VolleyOpt opt = VolleyOpt.getInstance();
		//opt.cancelReq(tag);
	}

}
