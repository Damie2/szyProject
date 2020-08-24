package com.smh.szyproject.test.zkr.dispatchTouchEvent;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author : smh
 * date   : 2020/8/19 15:48
 * desc   :
 */
public class ActivityEventTest extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_next)
    TextView textView ;
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        textView.setText("33333");
        L.e("222222");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        L.e("onTouchEvent");
        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        L.e("dispatchTouchEvent");
        //  https://www.cnblogs.com/chengxuyinli/p/9979826.html
        //如果返回true,表示已经对事件进行了处理，不用继续调用其余组件分发，并停止分发
        //如果是false，则表示不能对它进行处理，继续分发

        //如果子view消费了该事件，则返回true，让调用者知道已经被消费

        return super.dispatchTouchEvent(ev);
    }

    @OnClick(R.id.tv_next)
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.tv_next){
            L.e("我被点了");
        }
    }
}
