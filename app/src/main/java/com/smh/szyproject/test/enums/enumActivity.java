package com.smh.szyproject.test.enums;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/6/11 11:05
 * desc   :
 */
public class enumActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        
        method1();
        method2();
    }

    private void method2() {
        L.e(Color2.RED.toString());
    }


    public enum Color2 {
        RED("红色", 1), GREEN("绿色", 2), BLANK("白色", 3), YELLO("黄色", 4);
        // 成员变量
        private String name;
        private int index;

        // 构造方法
        private Color2(String name, int index) {
            this.name = name;
            this.index = index;
        }

        // 覆盖方法
        @Override
        public String toString() {
            return this.index + "_" + this.name;
        }
    }



    private void method1() {
        Color color = Color.RED;
        switch (color){
            case RED:
                break;
            case GREEN:
                break;
            case BLANK:
                break;
            default:
                break;
        }
    }

    public enum Color {
        RED, GREEN, BLANK, YELLOW
    }
}
