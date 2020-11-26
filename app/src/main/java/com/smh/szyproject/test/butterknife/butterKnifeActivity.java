package com.smh.szyproject.test.butterknife;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/11/25 14:51
 * desc   :
 */
public class butterKnifeActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }
    @Override
    public void init(Bundle savedInstanceState) {
        /**
         * butterKnife 使用APT
         * 在RUNTIME 时 通过apt   也就是 继承了AbstractProcessor 的一个类
         * 将所有注解进行扫描，然后将扫描到的annotation 和view放到map中一一绑定
         * 再接着通过第三方的javapoint，生成findViewById之类的java代码
         */
    }
}
