package com.smh.szyproject.test.proxy.dynamicProxy.two;

import com.smh.szyproject.other.utils.L;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * author : smh
 * date   : 2020/11/27 14:29
 *
 *  第三步
 *
 * desc   :   InvocationHandler一定要实现啊
 */
public class Proxyx implements InvocationHandler {

    private Object target;// 要代理的真实对象

    public Proxyx(Object target) {
        this.target = target;
    }

    /**
     *
     * @param proxy   真实对象的代理
     * @param method  调用真实对象的方法
     * @param args    数组，代理方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        L.e("代理class的名字:"+proxy.getClass().getName());
        L.e("before...");
        method.invoke(target,args);
        L.e("after...");
        return null;
    }
}
