package com.smh.szyproject.test.dagger2.module;

import dagger.Module;
import dagger.Provides;

/**
 * author : smh
 * date   : 2019/12/27 13:56
 * desc   : 第二步，新建UserModule 类用于对外部提供User类的实例
 */
@Module
public class UserModule {
    /**
     * Provides 负责提供数据
     */
    @Provides
    public User provideUser(){
        return new User();
    }

}
