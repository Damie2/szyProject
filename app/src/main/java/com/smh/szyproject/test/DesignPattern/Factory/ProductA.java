package com.smh.szyproject.test.DesignPattern.Factory;

import com.smh.szyproject.other.utils.L;

public class ProductA extends Product{
    @Override
    public void show() {
        L.e("我是ProductA");
    }
}
