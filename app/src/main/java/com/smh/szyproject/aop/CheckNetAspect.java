package com.smh.szyproject.aop;

import android.app.Application;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.core.content.ContextCompat;

import com.smh.szyproject.MyApplication;
import com.smh.szyproject.other.helper.ActivityStackManager;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.ToastUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * author : smh
 * date   : 2020/4/29 14:25
 * desc   :
 */
@Aspect
public class CheckNetAspect {

    /**
     * 方法切入点
     */
    @Pointcut("execution(@com.smh.szyproject.aop.CheckNet * *(..))")
    public void method() {
    }

    /**
     * 在连接点进行方法替换
     */
    @Around("method() && @annotation(checkNet)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, CheckNet checkNet) throws Throwable {
        Application application = ActivityStackManager.getInstance().getApplication();
        if (application != null) {
            ConnectivityManager manager = ContextCompat.getSystemService(application, ConnectivityManager.class);
            if (manager != null) {
                NetworkInfo info = manager.getActiveNetworkInfo();
                // 判断网络是否连接
                if (info == null || !info.isConnected()) {
                    ToastUtils.showToastForText(MyApplication.getContext(), "当前没有网络连接，请检查网络设置");
                    L.e("当前没有网络连接，请检查网络设置");
                    return;
                }
            }
        }
        //执行原方法
        joinPoint.proceed();
    }
}