package org.ko.aop.cglib;

import org.mockito.cglib.proxy.MethodInterceptor;
import org.mockito.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class DemoMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        Object result;
        try {
            System.out.println("before in cglib.");
            result = proxy.invokeSuper(obj, args);
            System.out.println("after returning in cglib.");
        } catch (Exception e) {
            System.out.println("exception");
            throw e;
        } finally {
            System.out.println("after finally");
        }
        return result;
    }
}
