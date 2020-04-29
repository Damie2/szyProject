package com.smh.szyproject.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : smh
 * date   : 2020/4/29 14:32
 * desc   :
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Permissions {
    /**
     * 需要申请权限的集合
     */
    String[] value();
}
