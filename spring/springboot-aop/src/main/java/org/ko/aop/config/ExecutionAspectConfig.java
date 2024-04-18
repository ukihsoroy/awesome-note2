package org.ko.aop.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionAspectConfig {

    @Pointcut("execution(public * org.ko.aop..service.*(..)")
    public void matchCondition () {

    }

    @Before("matchCondition()")
    public void before () {
        System.out.println("");
        System.out.println("###before");

    }

    @Before("matchCondition() && args(productId)")
    public void beforeArgs (Long productId) {
        System.out.println("###args = {}" + productId);
    }

    @Around("matchCondition()")
    public Object around (ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            System.out.println("###before");
            result = joinPoint.proceed(joinPoint.getArgs());
            System.out.println("###after returning");
        } catch (Throwable e) {
            System.out.println("###exception");
//            throw new
            e.printStackTrace();
        } finally {
            System.out.println("###after finally");
        }

        return result;
    }
}
