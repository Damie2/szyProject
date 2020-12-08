package com.smh.szyproject.test.proxy.staticProxy.two;

import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/11/27 14:19
 * desc   :
 */
public class ProxyObjcet extends AbstractObject{

    private RealObject realObject;

    public ProxyObjcet(RealObject realObject) {
        this.realObject = realObject;
    }

    @Override
    protected void operation() {
        L.e("开始啊");
        if(realObject==null){
            realObject = new RealObject();
        }
        realObject.operation();
        L.e("我是第三步啊");
    }
}
