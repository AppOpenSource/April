package com.abt.price.di.module;

import android.app.Activity;

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
    private Activity activity;

    public AppModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    Gank providerGank() {
        return new Gank();
    }

    @Provides
    Activity providesActivity() {
        // 提供activity对象　
        return activity;
    }

}
