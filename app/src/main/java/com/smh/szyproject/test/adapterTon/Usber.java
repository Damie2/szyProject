package com.smh.szyproject.test.adapterTon;

import com.smh.szyproject.other.utils.L;

/**
 *适配器模式
 */
public class Usber implements Usb{

    @Override
    public void isUsb() {
        L.e("Usb口");
    }
}
