package com.smh.szyproject.test.dagger2.inject;

import dagger.Component;

/**

 * 第二步，创建桥梁，链接Person和需要进行依赖注入类之间的桥梁
 */
@Component
public interface PersonComponent {
    //这个是桥梁
    void inject(daggerTest test);
}
