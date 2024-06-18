package org.ko.aop.pattern;

public class Main {

    public static void main(String[] args) {
        Proxy proxy = new Proxy(new RealSubject());
        proxy.request();
    }
}
