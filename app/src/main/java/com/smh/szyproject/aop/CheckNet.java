package com.smh.szyproject.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : smh
 * date   : 2020/4/29 14:25
 * desc   :
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface CheckNet {
}
