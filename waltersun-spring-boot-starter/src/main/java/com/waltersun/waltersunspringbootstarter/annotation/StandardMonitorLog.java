package com.waltersun.waltersunspringbootstarter.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 监控扫描日志
 *
 * @author 道禹 Walter
 * @date 2022-04-11
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface StandardMonitorLog {
    /**
     * type:hsf consumer or provider
     * @return str
     */
    String type() default "provider";
}
