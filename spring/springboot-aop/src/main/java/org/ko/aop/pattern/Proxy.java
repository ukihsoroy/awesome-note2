package org.ko.aop.pattern;

public class Proxy implements Subject {

    private RealSubject subject;

    public Proxy (RealSubject subject) {
        this.subject = subject;
    }

    @Override
    public void request() {
        try {
            System.out.println("before");
            subject.request();
            System.out.println("after returning");
        } catch (Exception e) {
            System.out.println("exception");
            e.printStackTrace();
        } finally {
            System.out.println("after finally");
        }
    }
}
