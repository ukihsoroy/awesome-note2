package org.ko.aop.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ModelTypeAspectConfig {

    /**
     * 匹配AOP对象的目标对象指定类型的方法, 即LogService的aop代理对象的方法
     */
    @Pointcut("this(org.ko.aop.log.LogService)")
    public void _this () {

    }

    /**
     * 匹配实现ProductService接口的目标对象(而不是aop代理后的对象)的方法, 这些即Loggable的方法
     */
    @Pointcut("targer(org.ko.aop.log.Loggable)")
    public void target () {

    }

    /**
     * 匹配所有以Service结尾的Bean的方法
     */
    @Pointcut("bean(*Service)")
    public void bean () {

    }

}
