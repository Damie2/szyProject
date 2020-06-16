package com.smh.szyproject.test.DesignPattern.Factory;

import com.smh.szyproject.other.utils.L;

public class ProductB extends  Product{
    @Override
    public void show() {
        L.e("我是ProductB");
    }
}
