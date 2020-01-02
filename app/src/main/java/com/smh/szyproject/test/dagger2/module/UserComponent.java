package com.smh.szyproject.test.dagger2.module;

import dagger.Component;

/**
 * author : smh
 * date   : 2019/12/27 13:59
 * desc   : 第三步，创建一个桥梁，作为依赖注入的入口
 */
@Component(modules = {UserModule.class})
public interface UserComponent  {

    void inject(UserDaggerTest test);
}
