package com.abt.price.core;

import com.abt.price.core.http.HttpHelper;

/**
 * @描述： @DataManager
 * @作者： @黄卫旗
 * @创建时间： @13/09/2018
 */
public class DataManager implements HttpHelper {

    private HttpHelper httpHelper;

    public DataManager(HttpHelper httpHelper) {
        this.httpHelper = httpHelper;
    }

    @Override
    public void getData() {
        httpHelper.getData();
    }
}
