package com.smh.szyproject.test.invoke;

import com.smh.szyproject.utils.L;

public class Test {
    private String colunm = "我是字段";

    public void shao(String message) {
        L.e("我被反射了:" + message);

    }

    public boolean boo(String aa, boolean b) {
        L.e("参数是："+aa);
        return false;
    }

    public void shao1() {
        L.e("我被反射了1:");
    }

    public void setColunm(String c) {
        L.e("现在的内容是：" + c);
    }
}
