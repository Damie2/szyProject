package com.smh.szyproject.test.UMTest;

import android.os.Bundle;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import butterknife.BindView;

/**
 * author : smh
 * date   : 2020/5/8 13:20
 * desc   : 推送的地址要写起始页
 */
public class UMTestActivity extends BaseActivity {

    @BindView(R.id.tv_next)
    TextView textView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
        textView.setOnClickListener(ssss -> { //Landba表达式
            L.e("我被点击了");
//            val sumLandba: (Int, Int) -> Int = { x, y -> x + y }
//            L.e(sumLandba(1, 2).toString())
        });
    }
}
