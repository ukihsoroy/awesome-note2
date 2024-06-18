package org.ko.aop.config;

import org.aspectj.lang.annotation.Pointcut;

public class AnnotationTypeAspectConfig {

    /**
     * 匹配方法标注有AdminOnly的注解的方法
     */
    @Pointcut("@annotation(org.ko.aop.security.AdminOnly)")
    public void annotation () {

    }

    /**
     * 匹配标注有Beta的类底下的方法, 要求的annotation的RetentionPolicy级别为CLASS
     */
    @Pointcut("@within(com.google.common.annotations.Beta)")
    public void within () {

    }

    /**
     * 匹配标注有Repository的类底下的方法, 要求的annotation的RetentionPolicy级别为RUNTIME
     */
    @Pointcut("@target(org.springframework.stereotype.Repository)")
    public void target () {

    }

    /**
     * 匹配传入的参数类标注有Repository注解的方法
     */
    @Pointcut("@args(org.springframework.stereotype.Repository)")
    public void args () {

    }
}
