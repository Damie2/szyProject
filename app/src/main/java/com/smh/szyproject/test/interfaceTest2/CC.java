package com.smh.szyproject.test.interfaceTest2;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

/**
 * author : smh
 * date   : 2020/3/9 15:30
 * desc   : 第二步 在被调用的地方赋值
 */
public class CC extends Dialog {
    InterfaceC interfacC;

    public CC(@NonNull Context context, InterfaceC interfacC) {
        super(context);
        this.interfacC = interfacC;
        interfacC.OnListener("321");
    }
}
