package com.abt.price.di.component;

import com.abt.price.di.module.AppModule;
import com.abt.price.di.module.GankApiModule;
import com.abt.price.ui.fragment.GankFragment;

import dagger.Component;

/**
 * @描述： @AppComponent
 * @作者： @黄卫旗
 * @创建时间： @14/09/2018
 */
@Component(modules = { AppModule.class, GankApiModule.class })
public interface AppComponent {
    void inject(GankFragment gankFragment);
}
