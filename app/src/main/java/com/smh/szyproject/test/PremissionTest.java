package com.smh.szyproject.test;

import android.os.Bundle;
import android.widget.ImageView;
import com.smh.szyproject.R;
import com.smh.szyproject.test.annotationTest.CherryAnnotation;
import com.smh.szyproject.base.BaseActivity;
import com.smh.szyproject.utils.L;

import java.lang.reflect.Method;

import butterknife.BindView;
public class PremissionTest extends BaseActivity {
    @BindView(R.id.iv)
    ImageView test;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        getAnnotation();
    }

    private void getAnnotation(){
        try {
            //获取class对象
            Class stuClass = Class.forName("com.smh.szyproject.test.annotationTest.TestRuntimeAnnotation");
            //获取注解的那个方法
            Method stuMethod = stuClass.getMethod("study",int.class);
            if(stuMethod.isAnnotationPresent(CherryAnnotation.class)){
                L.e("这个study上配置了CherryAnnotation注解");
                CherryAnnotation cherryAnnotation = stuMethod.getAnnotation(CherryAnnotation.class);
                L.e("注解上的姓名是"+cherryAnnotation.name()+"年龄是:"+cherryAnnotation.age()+"第二个数组是:"+cherryAnnotation.score()[1]);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
