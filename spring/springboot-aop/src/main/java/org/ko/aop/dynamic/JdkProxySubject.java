package org.ko.aop.dynamic;

import org.ko.aop.pattern.RealSubject;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class JdkProxySubject implements InvocationHandler {

    private RealSubject subject;

    public JdkProxySubject(RealSubject subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        try {
            System.out.println("before");
            result = method.invoke(subject, args);
            System.out.println("after returning");
        } catch (Exception e) {
            System.out.println("exception");
            throw e;
        } finally {
            System.out.println("after finally");

        }
        return result;
    }

}
