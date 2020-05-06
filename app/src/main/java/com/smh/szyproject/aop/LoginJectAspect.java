package com.smh.szyproject.aop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.Keep;
import androidx.fragment.app.Fragment;

import com.smh.szyproject.MyApplication;
import com.smh.szyproject.ui.activity.LoginActivity;
import com.smh.szyproject.utils.L;
import com.smh.szyproject.utils.SPUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
@Keep
public class LoginJectAspect {
    @Keep
    @Pointcut("execution(@com.smh.szyproject.aop.Login * *(..))")
    public void methodAnnotatedWithBehaviorTrace() {
    }

    /**
     * 现在需求是登录
     * keep是防止被混淆的
     */

//    @Login(className = "com.smh.szyproject.ui.activity.AboutActivity")
    //    @Login()
//    public void tv_next(View view) {
//    }

    @Keep
    @Around("methodAnnotatedWithBehaviorTrace()")
    public void weaveJoinPoint(ProceedingJoinPoint point)throws Throwable  {
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        Login checkLogin = methodSignature.getMethod().getAnnotation(Login.class);
        if (checkLogin != null){
            String token = SPUtil.getString("token", null, MyApplication.getContext());
            if (!TextUtils.isEmpty(token)){
                point.proceed();
            }else {
                Object object = point.getThis();
                Context context = null;
                if(object instanceof  Context){
                    context = (Context) object;
                }else if (object instanceof Fragment) {
                    context = ((Fragment) object).getActivity();
                }else if(object instanceof Activity){
                    context = (Context) object;
                }
                if (context == null ){
                    return;
                }
                Intent intent =  new Intent(context, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if(!TextUtils.isEmpty(checkLogin.className())){
                    intent.putExtra("className", checkLogin.className());
                }
                context.startActivity(intent);
            }
        }
    }
}
