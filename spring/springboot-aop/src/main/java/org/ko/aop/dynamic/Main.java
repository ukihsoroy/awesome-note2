package org.ko.aop.dynamic;

import org.ko.aop.pattern.RealSubject;
import org.ko.aop.pattern.Subject;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Subject subject = (Subject) Proxy.newProxyInstance(Main.class.getClassLoader(),
                new Class[]{Subject.class}, new JdkProxySubject(new RealSubject()));

        subject.request();
    }
}
