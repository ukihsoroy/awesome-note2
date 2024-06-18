package org.ko.aop.cglib;

import org.ko.aop.pattern.RealSubject;
import org.ko.aop.pattern.Subject;
import org.mockito.cglib.proxy.Enhancer;

public class Main {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        enhancer.setCallback(new DemoMethodInterceptor());
        Subject subject = (Subject)enhancer.create();
        subject.request();
    }
}
