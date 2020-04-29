package com.smh.szyproject.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : smh
 * date   : 2020/4/29 14:14
 * desc   :
 */
@Target(ElementType.METHOD)//作用于方法上
@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface SingleClick {
    /**
     * 快速点击的间隔
     */
    long value() default 1000;
}
