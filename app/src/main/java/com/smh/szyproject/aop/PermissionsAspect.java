package com.smh.szyproject.aop;

import androidx.annotation.Keep;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.XXPermissions;
import com.smh.szyproject.MyApplication;
import com.smh.szyproject.helper.ActivityStackManager;
import com.smh.szyproject.utils.ToastUtils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import java.util.List;

/**
 * author : smh
 * date   : 2020/4/29 14:33
 * desc   :
 */
@Aspect
@Keep
public class PermissionsAspect {
    /**
     * 方法切入点
     */
    @Keep
    @Pointcut("execution(@com.smh.szyproject.aop.Permissions * *(..))")
    public void method() {}

    @Keep
    @Around("method() && @annotation(permissions)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, Permissions permissions) {
        XXPermissions.with(ActivityStackManager.getInstance().getTopActivity())
                .permission(permissions.value())
                .request(new OnPermission() {

                    @Override
                    public void hasPermission(List<String> granted, boolean all) {
                        if (all) {
                            try {
                                // 获得权限，执行原方法
                                joinPoint.proceed();
                            } catch (Throwable e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        if (quick) {
                            ToastUtils.showToastForText(MyApplication.getContext(),"授权失败，请手动授予权限");
                            XXPermissions.gotoPermissionSettings(ActivityStackManager.getInstance().getTopActivity(), false);
                        } else {
                            ToastUtils.showToastForText(MyApplication.getContext(),"请先授予权限");
                        }
                    }
                });

    }
}
