package com.longtu.datamove.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface OpLog {

    /**
     * 操作模块儿
     * @return
     */
    String module() ;

    /**
     * 操作方法
     * @return
     */
    String opMethod();

}
