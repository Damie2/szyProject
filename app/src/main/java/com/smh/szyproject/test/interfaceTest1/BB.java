package com.smh.szyproject.test.interfaceTest1;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

/**
 * author : smh
 * date   : 2020/3/9 15:30
 * desc   : 第二步 在被调用的地方赋值
 */
public class BB extends Dialog {
    InterfaceB interfaceB;


    public BB(@NonNull Context context, InterfaceB interfaceB) {
        super(context);
        this.interfaceB = interfaceB;
        interfaceB.OnListener("123");
    }
}
