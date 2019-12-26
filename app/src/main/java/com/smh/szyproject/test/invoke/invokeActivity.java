package com.smh.szyproject.test.invoke;


import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.utils.L;
import com.smh.szyproject.utils.ToastUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class invokeActivity extends BaseActivity {
    String path = "com.smh.szyproject.test.invoke.Test";

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        try {
//            invokeMethod1();//不带参
//            invokeMethod2();//带参
//            invokeMethod3();//带参   2和3是一样的，只不过写法不同
//            invokeMethod4();//获取方法体的参数类型和设值
//            invokeMethod5();//获取返回类型

            invokeMethod6();////反射字段，获取内容，使用新的内容，调用方法
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    /**
     * 现在的内容是：???
     */
    private void invokeMethod6() throws Exception {
        Class c = Class.forName(path);
        //获取某个类所有声明的字段，包括public  private  protected 但是不包括父类的声明字段
        Field[] fields = c.getDeclaredFields();
        //获取构造函数
        Constructor constructor = c.getConstructor();
        //构造函数实例化
        Object  o  = constructor.newInstance();
        //循环所有字段
        for(Field field:fields){
            //如果发现有个字段叫colunm
            if(field.getName().equals("colunm")){
                //就算它是私有的，加上这个
                field.setAccessible(true);
                //然后把字段替换成？？？
                field.set(o,"???");
                //接收找到setColunm方法，把替换的？？？放上去
                Method m=c.getMethod("setColunm", String.class);
                //执行反射
                m.invoke(o,field.get(o));
            }
        }

    }

    /**
     * 返回类型 :void
     */
    private void invokeMethod5()  throws Exception {
        Class c = Class.forName(path);
        Method[] methods = c.getMethods();
        for(Method m : methods){
            if(m.getName().equals("setColunm")){
                Class returnType = m.getReturnType();
                L.e("返回类型 :" + returnType.getName());
            }
        }
    }

    /**
     *  参数是：111
     *  参数类型是:java.lang.String
     *  参数类型是:boolean
     *
     */
    //获取方法体的参数类型和设值
    private void invokeMethod4() throws Exception {
        Class c = Class.forName(path);
        Object o = c.newInstance();
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            if(method.getName().equals("boo")){
                method.invoke(o,"111",true);
                for(Class param:method.getParameterTypes()){
                    L.e("参数类型是:" + param.getName());
                }
            }
        }
    }

    //直接执行方法体，并将参数填上去
    private void invokeMethod3() throws Exception {
        Class c = Class.forName(path);
        Object o = c.newInstance();
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("shao")) {
                method.invoke(o, "2222");
            }
        }
    }

    //直接执行方法体，并将参数填上去
    private void invokeMethod2() throws Exception {
        Class c = Class.forName(path);
        Method m = c.getMethod("shao", String.class);
        m.invoke(c.newInstance(), "123");
    }

    //直接执行shao1方法体
    private void invokeMethod1() throws Exception {
        Class c = Class.forName(path);
        Object o = c.newInstance();
        Method[] methods = c.getMethods();
        for (Method method : methods) {
            //必须有equals,，否则会报异常
            if (method.getName().equals("shao1")) {
                method.invoke(o);
            }
        }
    }
}
