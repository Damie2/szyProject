package com.smh.szyproject.test.butterknife.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * author : smh
 * date   : 2020/11/25 15:00
 * desc   :
 */
@Documented
@Target(ElementType.TYPE) //TYPE表名是用于描述类或者接口的
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface simple {
   public String doTest();
}
