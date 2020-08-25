package com.smh.szyproject.test.DiGui;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;

import java.io.File;

/**
 * author : smh
 * date   : 2020/8/25 13:14
 * desc   :
 */
public class RecursionActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.test_activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        getContentView().postDelayed(()->{
            method1(new File(""));
        },1000);
    }
    //删除全部的文件
    private void method1(File file) {
        //1:判断目录是否存在
        if(file.exists()){
            //1:判断是否是文件夹
            if(file.isDirectory()){
                //2 获取文件数组
                File[] file2 = file.listFiles();
                //3 遍历文件夹
                for (File file3 : file2) {
                    //4:将遍历出来的文件以及目录传入方法中
                    //自己调自己
                    method1(file3);
                }
            }
            //直接删除文件
            file.delete();
        }
    }
}
