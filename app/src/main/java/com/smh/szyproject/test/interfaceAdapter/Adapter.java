package com.smh.szyproject.test.interfaceAdapter;

import com.smh.szyproject.other.utils.L;

public abstract class Adapter implements A{

    @Override
    public void a() {
        L.e("1");
    }

    @Override
    public void b() {
        L.e("2");
    }

    @Override
    public void c() {
        L.e("3");
    }

    @Override
    public void d() {
        L.e("4");
    }

    @Override
    public void e() {
        L.e("5");
    }
}
