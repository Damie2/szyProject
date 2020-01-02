package com.smh.szyproject.test.proxy.dynamicProxy;

import com.smh.szyproject.utils.L;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * author : smh
 * date   : 2019/12/31 14:57
 * desc   :
 */
public class SubjectInvocationHandler implements InvocationHandler {
    //这个是我们系统代理的真实对象
    private Object subject;

    public SubjectInvocationHandler(Object subject){
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        L.e("开始invoke");
        L.e("方法是:"+method);
        method.invoke(subject,args);
        L.e("结束invoke");
        return null;
    }
}
