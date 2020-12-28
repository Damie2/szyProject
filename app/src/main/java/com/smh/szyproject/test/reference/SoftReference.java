package com.smh.szyproject.test.reference;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.test.recycleView.pubu.Bean;

import java.lang.ref.WeakReference;

/**
 * author : smh
 * date   : 2020/12/21 13:31
 * desc   : 强引用  软引用  弱引用 虚引用
 */
public  class SoftReference  extends BaseActivity {

    public SoftReference(Bean bean) {
        super();
    }

    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        strongReference();
        softReference();
        weekReference();
        phantomReference();
    }

    /**
     * 虚引用
     * 如果一个对象和虚引用关联，则跟没有引用关联一样
     * 什么时候都能被回收
     */
    private void phantomReference() {

    }

    /**
     * 弱引用
     * 弱引用描述非必须对象，当JVM回收的时候，无论内存是否充足，就回收被弱引用关联的对象
     *
     */
    private void weekReference() {
        WeakReference<String> st = new WeakReference<String>(new String("??"));
        L.e(""+st.get());
        System.gc();
        L.e(""+st.get());
    }

    /**
     * 软引用,内存不足的时候，JVM会回收它
     */
    private void softReference() {
        SoftReference softReference=new SoftReference(new Bean("123"));
    }

    /**
     * 强引用  这种赋值的，new的就是强引用
     */
    private void strongReference() {
        String str = "123";
        Object o = new Object();
    }
}
