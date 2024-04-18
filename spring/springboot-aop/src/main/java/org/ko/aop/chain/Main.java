package org.ko.aop.chain;

public class Main {

    static class HandlerA extends Handler {
        @Override
        protected void handlerProcess() {
            System.out.println("handler a");
        }
    }

    static class HandlerB extends Handler {
        @Override
        protected void handlerProcess() {
            System.out.println("handler b");
        }
    }

    static class HandlerC extends Handler {
        @Override
        protected void handlerProcess() {
            System.out.println("handler c");
        }
    }

    public static void main(String[] args) {
        Handler a = new HandlerA();
        Handler b = new HandlerB();
        Handler c = new HandlerC();
        b.setSucessor(c);
        a.setSucessor(b);
        a.execute();
    }

}
