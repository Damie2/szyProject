package com.smh.szyproject.test.interfaceTest3;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

/**
 * author : smh
 * date   : 2020/3/9 15:30
 * desc   : 第二步 在被调用的地方赋值
 */
public class DD extends Dialog {
    InterfaceD interfacC;

    public DD(@NonNull Context context, int themeResId, InterfaceD interfacC) {
        super(context, themeResId);
        this.interfacC = interfacC;
    }

    public DD(@NonNull Context context, InterfaceD interfacC) {
        super(context);
        this.interfacC = interfacC;
        //哪里用，就哪里调这个就哪里调这个接口
        interfacC.OnListener("321");
    }
}
