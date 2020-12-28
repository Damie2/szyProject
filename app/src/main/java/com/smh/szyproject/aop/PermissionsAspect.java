package com.smh.szyproject.aop;

import android.app.Activity;

import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.smh.szyproject.other.helper.ActivityStackManager;
import com.smh.szyproject.other.utils.L;
import com.smh.szyproject.other.utils.ToastUtils;

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
public class PermissionsAspect {
    /**
     * 方法切入点
     */
    @Pointcut("execution(@com.smh.szyproject.aop.Permissions * *(..))")
    public void method() {}

    @Around("method() && @annotation(permissions)")
    public void aroundJoinPoint(final ProceedingJoinPoint joinPoint, Permissions permissions) {
        Activity activity = ActivityStackManager.getInstance().getTopActivity();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            return;
        }

        XXPermissions.with(activity)
                // 申请安装包权限
                //.permission(Permission.REQUEST_INSTALL_PACKAGES)
                // 申请悬浮窗权限
                //.permission(Permission.SYSTEM_ALERT_WINDOW)
                // 申请通知栏权限
                //.permission(Permission.NOTIFICATION_SERVICE)
                // 申请系统设置权限
                //.permission(Permission.WRITE_SETTINGS)
                // 支持请求6.0悬浮窗权限8.0请求安装权限
                .permission(permissions.value())
                .request(new OnPermissionCallback() {

                    @Override
                    public void onGranted(List<String> permissions, boolean all) {
                        if (all) {
                            try {
                                // 获得权限，执行原方法
                                joinPoint.proceed();
                            } catch (Throwable e) {
                                e.printStackTrace();
                            }
                        }else{
                            L.e("NO");
                        }
                    }

                    @Override
                    public void onDenied(List<String> permissions, boolean quick) {
                        if (quick) {
                            ToastUtils.showToastForText(ActivityStackManager.getInstance().getTopActivity(),"授权失败，请手动授予权限");
                            XXPermissions.startPermissionActivity(ActivityStackManager.getInstance().getTopActivity(), permissions);
                        } else {
                            ToastUtils.showToastForText(ActivityStackManager.getInstance().getTopActivity(),"请先授予权限");
                        }
                    }
                });
    }
}
