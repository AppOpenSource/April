package com.abt.price.app;

import com.abt.common.app.BasicApplication;
import com.abt.price.di.AppComponent;
import com.abt.price.di.AppModule;
import com.abt.price.di.DaggerAppComponent;
import com.abt.price.di.GithubApiModule;

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
        setupCompoent();
    }

    private void setupCompoent() {
        appComponent = DaggerAppComponent.builder()
                .githubApiModule(new GithubApiModule())
                .appModule(new AppModule(this))
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
