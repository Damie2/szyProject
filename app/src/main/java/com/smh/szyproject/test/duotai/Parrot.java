package com.smh.szyproject.test.duotai;

import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/8/13 10:24
 * desc   : 有只鹦鹉，继承了鸟
 */
public  class Parrot extends Bird{
    @Override
    public void moo() {
        L.e("鹦鹉叫");
    }
}
