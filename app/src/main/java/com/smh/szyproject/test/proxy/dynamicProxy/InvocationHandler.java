package com.smh.szyproject.test.proxy.dynamicProxy;

import java.lang.reflect.Method;

/**
 * author : smh
 * date   : 2019/12/31 14:31
 * desc   : 动态代理类
 *          第一步创建接口
 */
public interface InvocationHandler {
    /**
     *
     * @param proxy  生成的代理对象
     * @param method 指代我们所要调用真实对象的某个 方法的Method对象
     * @param args 指调用真实对象某个方法时接受的参数
     * @return
     * @throws Throwable
     */
        public Object invoke(Object proxy, Method method,Object[] args) throws  Throwable;
}
