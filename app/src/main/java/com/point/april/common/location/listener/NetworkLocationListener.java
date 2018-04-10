package com.point.april.common.location.listener;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by weiqi.huang on 2016/10/28.
 */
// Network监听的回调函数
public class NetworkLocationListener implements LocationListener {

    private String TAG = NetworkLocationListener.class.getSimpleName();
    private Context mContext;

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d(TAG, "onStatusChanged provider : "+provider);
        Log.d(TAG, "onStatusChanged status : "+status);
        Log.d(TAG, "onStatusChanged extras : "+extras);
    }

    @Override
    public void onProviderEnabled(String provider) {
        Log.d(TAG, "onProviderEnabled provider : "+provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        Log.d(TAG, "onProviderDisabled provider : "+provider);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.d(TAG, "onLocationChanged location : "+location);
        if (location != null) {
            double latitude = location.getLatitude(); // 经度
            double longitude = location.getLongitude(); // 纬度
            double altitude = location.getAltitude(); // 海拔
            Log.d(TAG, "location: "+latitude+"-"+longitude+"-"+altitude);
            // Toast.makeText(mContext, "location: "+latitude+"-"+longitude+"-"+altitude, Toast.LENGTH_SHORT).show();
        }
    }
}
