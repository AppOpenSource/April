package com.abt.price.di;

import dagger.Module;
import dagger.Provides;

/**
 * @描述： @MainModule
 * @作者： @黄卫旗
 * @创建时间： @14/09/2018
 */
@Module
public class MainModule {
    //提供依赖对象的实例

    // 关键字，标明该方法提供依赖对象
    @Provides
    Repo providerPerson() {
        return new Repo();
    }
    //提供Person对象

}
