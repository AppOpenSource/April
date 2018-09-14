package com.abt.price.app;

import com.abt.common.app.BasicApplication;
import com.abt.price.di.component.AppComponent;
import com.abt.price.di.component.DaggerAppComponent;
import com.abt.price.di.module.AppModule;
import com.abt.price.di.module.GankApiModule;

/**
 * @描述： @PriceApp
 * @作者： @黄卫旗
 * @创建时间： @10/06/2018
 */
public class PriceApp extends BasicApplication {

    private static PriceApp sInstance;
    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.sInstance = this;
        setupComponent();
    }

    private void setupComponent() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .gankApiModule(new GankApiModule())
                .build();
    }

    public static PriceApp getsInstance() {
        return sInstance;
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void initComplete() {

    }

}
