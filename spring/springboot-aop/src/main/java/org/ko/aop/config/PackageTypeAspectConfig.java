package org.ko.aop.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 匹配ProductService类里面所有方法
 * @Pointcut("within()")
 * 匹配org下面的全部
 * @Pointcut("within(org.*)")
 */
@Aspect
@Component
public class PackageTypeAspectConfig {

    @Pointcut("within(org.ko.aop.service.ProductService)")
    public void matchType () {

    }

    @Before("matchType()")
    public void before () {
        System.out.println("before");
    }
}
