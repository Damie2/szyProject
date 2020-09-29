package com.smh.szyproject.aop;

import android.app.Activity;

import com.hjq.permissions.OnPermission;
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
                // 可设置被拒绝后继续申请，直到用户授权或者永久拒绝
//                .constantRequest()
                // 支持请求6.0悬浮窗权限8.0请求安装权限
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
                        }else{
                            L.e("NO");
                        }
                    }

                    @Override
                    public void noPermission(List<String> denied, boolean quick) {
                        L.e("???");
                        if (quick) {
                            ToastUtils.showToastForText(ActivityStackManager.getInstance().getTopActivity(),"授权失败，请手动授予权限");
                            XXPermissions.startPermissionActivity(ActivityStackManager.getInstance().getTopActivity(), denied);
                        } else {
                            ToastUtils.showToastForText(ActivityStackManager.getInstance().getTopActivity(),"请先授予权限");
                        }
                    }
                });
    }
}
