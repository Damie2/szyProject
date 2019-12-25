package com.smh.szyproject.aop;

import android.text.TextUtils;

import androidx.annotation.Keep;

import com.smh.szyproject.MyApplication;
import com.smh.szyproject.utils.L;
import com.smh.szyproject.utils.SPUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
@Keep
public class LoginJectAspect {
    @Keep
    @Pointcut("execution(@com.smh.szyproject.aop.Login * *(..))")
    public void methodAnnotatedWithBehaviorTrace() {
    }

    /**
     * 现在需求是登录
     * @param joinPoint
     * keep是防止被混淆的
     */

    @Keep
    @Around("methodAnnotatedWithBehaviorTrace()")
    public void weaveJoinPoint(ProceedingJoinPoint joinPoint) {
        try {
            String token = SPUtil.getString("String", null, MyApplication.getContext());
//            L.e("准备跳往login页面");
            if(TextUtils.isEmpty(token)){
                L.e("已登录");
                joinPoint.proceed();
            }else{
                L.e("准备跳往login页面");
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
