package com.smh.szyproject.test.dagger2.inject;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

import javax.inject.Inject;

public class daggerTest  extends BaseActivity {

    /**
     * inject注解用于工程中自己建立的类是ok的，但是对开源库中的其他类就不行了
     * 这个时候需要用到module和provides
     */


    @Inject
    Person person;
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        //第三步，先build,不然DaggerPersonComponent出不来
        DaggerPersonComponent.builder().build().inject(this);
        L.e(person.getName());
    }
}
