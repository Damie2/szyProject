package com.smh.szyproject.test.customView;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

/**
 * author : smh
 * date   : 2020/8/24 17:45
 * desc   :
 */
public class CustomViewActivity extends BaseActivity {
    /**
     * 当Activity调用setContentView时，会调用PhoneWindow里的setContentView方法
     * phoneWindow的setContentView会生成一个DecorView对象
     * DecorView包含跟布局，跟布局包括一个id为content的FrameLayout布局
     * Activity加载xml最后是通过LayoutInflater将xml文件的内容解析成View层级体系
     * 最后添加到id为centent的FrameLayout布局中
     *
     */

    @Override
    public int getLayoutId() {
        return R.layout.test_custom_view;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        getWindow().getDecorView();
    }
}
