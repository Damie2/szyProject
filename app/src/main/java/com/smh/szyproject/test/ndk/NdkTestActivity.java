package com.smh.szyproject.test.ndk;

import android.os.Bundle;

import com.smh.szyproject.R;
import com.smh.szyproject.common.base.BaseActivity;
import com.smh.szyproject.other.utils.L;

/**
 * author : smh
 * date   : 2020/6/4 14:27
 * desc   : ndk开发
 */
public class NdkTestActivity extends BaseActivity {

    /**
     *
     * 首先 build.gradle中配置如下
     *         externalNativeBuild {
     *             cmake {
     *                 cppFlags ""
     *             }
     *         }
     *
     *
     *     externalNativeBuild {
     *         cmake {
     *             path "src/main/cpp/CMakeLists.txt"
     *             version "3.10.2"
     *         }
     *     }
     *
     * 第二步 cpp中 添加CMakeLists.txt  ，路径和上面要对上
     *
     * 第三步native-lib.cpp中方法名是
     * Java_com_smh_szyproject_test_ndk_NdkTestActivity_stringFromJNI
     * com_smh_szyproject_test_ndk_  这个是调用类名的路径
     * NdkTestActivity 这个是调用的activity
     *
     * 接着就是
     *  public native String stringFromJNI();
     *
     *     static {
     *         System.loadLibrary("native-lib");
     *     }
     *这个写法了
     *
     *
     */


//    蓝牙和aidl
    @Override
    public int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
//        L.e("jni调用的值是:" + stringFromJNITwo());
        changeJavaStaticField();
        L.e("新啊："+sMake);
    }

    public native String stringFromJNI();

    public native  String stringFromJNITwo();

    /**
     * java普通方法调用C
     * @param text
     * @return
     */
    public native String stringFromJNIThree(String text);
    //这个是C++调用的当前方法
    public void callJavaMethod(String text){
        L.e("被调用了:"+text);
    }
    public static native String stringFromJNIFour(String text);

    /**
     * java静态方法调用C
     * @param text
     */
    public static void staticJNIFour(String text){
        L.e("我是静态方法被调用了:"+text);
    }

    /**
     * 修改String值
     */
    private String name = "我是对象字段";

    public native void changeJavaField();
    /**
     * 修改Int值
     */
    private static int sMake = 0;

    public native void changeJavaStaticField();


    static {
        System.loadLibrary("native-jni-lib");
    }


}
