package com.smh.szyproject.test.proxy.staticProxy.two;

import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/11/27 14:18
 * desc   :第二步 目标的实现类
 * */
public class RealObject extends AbstractObject{
    @Override
    protected void operation() {
        L.e("我是第二步");
    }
}
