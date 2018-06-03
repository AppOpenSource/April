package com.point.april.network;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;

import com.point.april.April;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NetworkUtil {
	
	private static final String TAG = NetworkUtil.class.getSimpleName();
	
	/**
     * 检查当前网络是否可用
     */
    public static boolean isNetworkAvailable(Activity activity) {
        Context context = activity.getApplicationContext();
        // 获取手机所有连接管理对象（包括对wi-fi, net等连接的管理）
        ConnectivityManager connectivityManager = (ConnectivityManager) 
        		context.getSystemService(Context.CONNECTIVITY_SERVICE);
       
        if (connectivityManager == null) {
            return false;
        } else {
            // 获取NetworkInfo对象
            NetworkInfo[] networkInfo = connectivityManager.getAllNetworkInfo();        
            if (networkInfo != null && networkInfo.length > 0){
                for (int i = 0; i < networkInfo.length; i++){
                    // 判断当前网络状态是否为连接状态
                    if (networkInfo[i].getState() == NetworkInfo.State.CONNECTED){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
	 * 判断是否有网络连接
	 */
	public static boolean isNetworkConnected(Context context) {  
		if (context != null) {  
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
					.getSystemService(Context.CONNECTIVITY_SERVICE);  
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();  
			if (mNetworkInfo != null) {  
				return mNetworkInfo.isAvailable()&&mNetworkInfo.isConnected();
			}
		}
		return false;  
	}
	
	/**
	 * 判断WIFI网络是否可用
	 */
	public static boolean isWifiConnected(Context context) {  
		if (context != null) {  
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
					.getSystemService(Context.CONNECTIVITY_SERVICE);  
			NetworkInfo mWiFiNetworkInfo = mConnectivityManager  
					.getNetworkInfo(ConnectivityManager.TYPE_WIFI);  
			if (mWiFiNetworkInfo != null) {  
				// LogManager.d("Network", "wifi conn="+mWiFiNetworkInfo.isConnected()+",state="+mWiFiNetworkInfo.getState().name());
				return mWiFiNetworkInfo.isAvailable()&&mWiFiNetworkInfo.isConnected();  
			}
		}
		return false;  
	}

	/**
	 * 判断MOBILE网络是否可用
	 */
	public static boolean isMobileConnected(Context context) {  
		if (context != null) {  
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
					.getSystemService(Context.CONNECTIVITY_SERVICE);  
			NetworkInfo mMobileNetworkInfo = mConnectivityManager  
					.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);  
			if (mMobileNetworkInfo != null) {  
				// LogManager.d("Network", "mobile conn="+mMobileNetworkInfo.isConnected()+",state="+mMobileNetworkInfo.getState().name());
				return mMobileNetworkInfo.isAvailable()&&mMobileNetworkInfo.isConnected();  
			}  
		}  
		return false;  
	}

	/**
	 * 获取当前网络连接的类型信息
	 * @param context
	 * @return ConnetivityManager.Type
	 */
	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}

	/**
	 * 检查所处网络是否是同一个局域网
	 */
	public static void checkServer() {
		ExecutorService mService = Executors.newCachedThreadPool();
		mService.submit(new Runnable() {
			@Override
			public void run() {
				WifiManager manager = (WifiManager) April.getInstance().getApplicationContext().getSystemService(April.getInstance().getApplicationContext().WIFI_SERVICE);
				WifiManager.MulticastLock lock= manager.createMulticastLock("CCAppWifi");
			}
		});
	}

	/**
	 * @category 判断是否有网络连接，比如局域网或者广域网
	 */
	public static final boolean ping(String ip) {
		String result = null;
		try {
			// String ip = "www.baidu.com";// ping的地址，可以换成任何一种可靠的外网
			Process p = Runtime.getRuntime().exec("ping -c 4 -w 9 " + ip);// ping网址3次
			// 读取ping的内容，可以不加
			InputStream input = p.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(input));
			StringBuffer stringBuffer = new StringBuffer();
			String content = "";
			while ((content = in.readLine()) != null) {
				stringBuffer.append(content);
			}
			// ping的状态
			int status = p.waitFor();
			if (status == 0) {
				result = "success";
				return true;
			} else {
				result = "failed";
			}
		} catch (IOException e) {
			result = "IOException";
		} catch (InterruptedException e) {
			result = "InterruptedException";
		} finally {
		}
		return false;
	}
    
}
