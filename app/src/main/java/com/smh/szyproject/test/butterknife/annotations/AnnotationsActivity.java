package com.smh.szyproject.test.butterknife.annotations;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * author : smh
 * date   : 2020/11/25 14:51
 * desc   :
 */
public class AnnotationsActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
            //元注解
//        @Documented
//         @Target  标明了适用范围  有7个值
//        @Retention()//描述生命周期的
//        @Inherited  表示可继承了，用了它说明会用于class的子类
    }
}
