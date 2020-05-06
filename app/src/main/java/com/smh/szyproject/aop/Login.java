package com.smh.szyproject.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)//作用于方法上
@Retention(RetentionPolicy.RUNTIME)//运行时
public @interface Login {
    String className() default "";
}
