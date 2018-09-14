package com.abt.price.di.module;

import android.app.Application;

import com.abt.price.core.bean.gank.Gank;

import dagger.Module;
import dagger.Provides;

/**
 * @描述： @AppModule
 * @作者： @黄卫旗
 * @创建时间： @14/09/2018
 */
@Module
public class AppModule {
    private Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    public Application provideApplication() {
        return application;
    }

    @Provides
    Gank providerGank() {
        return new Gank();
    }

}
