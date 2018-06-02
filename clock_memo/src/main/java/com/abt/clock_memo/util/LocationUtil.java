package com.abt.clock_memo.util;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.abt.clock_memo.global.GlobalConstant;
import com.abt.clock_memo.global.PreferenceConstant;
import com.abt.clock_memo.util.listener.GPSLocationListener;
import com.abt.clock_memo.util.listener.NetworkLocationListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @描述： @LocationUtil
 * @作者： @黄卫旗
 * @创建时间： @2016/10/28
 */
public class LocationUtil {

    private String TAG = LocationUtil.class.getSimpleName();
    private static LocationUtil mInstance;
    private Context mContext;
    private Location mLastKnownLocation;
    private GPSLocationListener mGps;
    private NetworkLocationListener mNetwork;
    private SimpleDateFormat mSDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSS");
    private LocationManager locationManager;

    public static LocationUtil getInstance() {
        if (mInstance == null) {
            mInstance = new LocationUtil();
        }
        return mInstance;
    }

    public void init() {
        if (mGps == null) {
            mGps = new GPSLocationListener();
        }
        if (mNetwork == null) {
            mNetwork = new NetworkLocationListener();
        }
    }

    public boolean signIn(Context context) {
        Log.d(TAG, "signIn()");
        // TIME
        long time = System.currentTimeMillis();
        String currentTime = DateChangeUtil.toStringMinuteDate(time);
        Log.d(TAG, "currentTime: "+currentTime);

        // PLACE
        LocationUtil mgr = LocationUtil.getInstance();
        mgr.getLocation(context);

        // PEOPLE
        long userId = 10001;
        String userName = PreferencesUtil.getString(mContext, PreferenceConstant.SHARE_VALUE_USER_NAME);
        String nickName = PreferencesUtil.getString(mContext, PreferenceConstant.SHARE_VALUE_NICK_NAME);
        Log.d(TAG, "userId: "+userId);
        Log.d(TAG, "userName: "+userName);
        Log.d(TAG, "nickName: "+nickName);

        // SIGN IN
        // signIn(time, place, userId);
        Log.d(TAG, "signIn()");
        return true;
    }

    public void getLocation(Context context) {
        mContext = context;
        Log.d(TAG, "getLocation()");
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            /** 进行定位
             * provider: 用于定位的locationProvider
             * minTime: 时间更新间隔，单位：ms
             * minDistance: 位置刷新距离，单位：m
             * listener: 用于定位更新的监听者locationListener
             */
            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                Log.d(TAG, "NETWORK_PROVIDER ENABLED");
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mNetwork);
            }

            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                Log.d(TAG, "GPS_PROVIDER ENABLED");
                // requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10, mGps);
            }

            /*final Location mlastKnownlocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (mlastKnownlocation != null) {
                getLocationDetail(mlastKnownlocation);
            }*/
        } else {
            // 无法定位：
            // 1、提示用户打开定位服务；
            // 2、跳转到设置界面；
            Toast.makeText(mContext, "无法定位，请打开定位服务", Toast.LENGTH_LONG).show();
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            mContext.startActivity(intent);
        }
    }

    public void stopLocate() {
        if (locationManager == null) return;

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) && mNetwork != null) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.removeUpdates(mNetwork);
        }

        if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) && mGps != null) {
            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.removeUpdates(mGps);
        }
    }

    public void getLocationDetail(Location location) {
        Log.d(TAG, "getLocationDetail()");
        double lat = location.getLatitude();
        // 获取经度
        double lon = location.getLongitude();
        // 位置提供者
        String provider = location.getProvider();
        // 位置的准确性
        float accuracy = location.getAccuracy();
        // 高度信息
        double altitude = location.getAltitude();
        // 方向角
        float bearing = location.getBearing();
        // 速度 米/秒
        float speed = location.getSpeed();

        String lastTime = PreferencesUtil.getString(mContext, GlobalConstant.LAST_LOCATION_TIME);
        String locationTime = mSDF.format(new Date(location.getTime()));

        long time = System.currentTimeMillis();
        String currentTime = DateChangeUtil.toStringMinuteDate(time);
        Log.d(TAG, "currentTime : " + currentTime);
        PreferencesUtil.write(mContext, GlobalConstant.LAST_LOCATION_TIME, currentTime);

        //if (myLocation != null) {
        //    currentTime = mSDF.format(new Date(myLocation.getTime()));
        //     myLocation = location;
        //} else {
        //    myLocation = location;
        //}

        // 获取当前详细地址
        Geocoder gc = new Geocoder(mContext, Locale.getDefault());
        List<Address> locationList = null;
        try {
            locationList = gc.getFromLocation(lat, lon, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Address address = locationList.get(0);//得到Address实例
        //Log.i(TAG, "address =" + address);
        String countryName = address.getCountryName();//得到国家名称，比如：中国
        Log.i(TAG, "countryName = " + countryName);
        String locality = address.getLocality();//得到城市名称，比如：北京市
        Log.i(TAG, "locality = " + locality);
        String addressLine = "";
        for (int i = 0; address.getAddressLine(i) != null; i++) {
            addressLine = address.getAddressLine(i);//得到周边信息，包括街道等，i=0，得到街道名称
            Log.i(TAG, "addressLine = " + addressLine);
        }

        String res = "经度：" + lon + "\n纬度：" + lat + "\n服务商：" + provider + "\n准确性："
                + accuracy + "\n高度：" + altitude + "\n方向角：" + bearing + "\n速度："
                + speed + "\n当前上报时间：" + currentTime + "\n上次上报时间：" + lastTime
                + "\n最新上报时间：" + locationTime + "\n您所在的国家：" + countryName
                + "\n您所在的城市：" + locality + "\n您所在的街道：" + addressLine;
        Log.d(TAG, res);

        Toast.makeText(mContext, res, Toast.LENGTH_SHORT).show();
    }

}
