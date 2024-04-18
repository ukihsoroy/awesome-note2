package org.ko.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 拦截器拿不到Http request, response对象
 */
//@Aspect
//@Component
public class TimeAspect {

    @Around("execution(* org.ko.web.controller.UserController.*(..))")
    public Object handlerControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start.");
        long startTime = new Date().getTime();
        Object r = pjp.proceed();
        for (Object o : pjp.getArgs()) {
            System.out.println("args is: " + o.toString());
        }
        System.out.println("time: " + (new Date().getTime() - startTime));
        System.out.println("time aspect end.");

        return r;
    }
}
