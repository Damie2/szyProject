package com.smh.szyproject.test.dagger;

import dagger.Component;

@Component(modules = {UserModule.class})//这是个连接器，相当于一个桥梁
//上面这句关联到module
public interface UserComponet {
    void inject(Dagger2Test dagger2Test);//这一句关联到activity
}
