package com.smh.szyproject.test.SubjectTon;

import com.smh.szyproject.utils.L;

/**
 * 第三,代理也实现了这个方法
 * 静态代理类
 */
public class Proxy implements Subject{

    private SuperMan superMan;
    //构造方法里传进来这个超人
    public Proxy(SuperMan superMan){
        this.superMan = superMan;
    }
    @Override
    public void shopping() {
        L.e("准备开始");
        superMan.shopping();
        L.e("完事之后");
    }
}
