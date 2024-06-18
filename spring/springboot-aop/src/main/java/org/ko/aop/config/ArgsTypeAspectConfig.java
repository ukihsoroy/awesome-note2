package org.ko.aop.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ArgsTypeAspectConfig {

    /**
     * 匹配任何以find开头而且只有一个Long参数的方法
     */
    @Pointcut("execution(* *..find*(Long))")
    public void args1 () {

    }

    /**
     * 匹配任何只有一个Long参数的方法
     */
    @Pointcut("args(Long)")
    public void args2 () {

    }

    /**
     * 匹配任何以find开头的而且第一个参数为Long型的方法
     */
    @Pointcut("execution(* *..find*(Long,..)")
    public void args3 () {

    }

    /**
     * 匹配第一个参数为Long型的方法
     */
    @Pointcut("args(Long,..)")
    public void args4 () {

    }

}
