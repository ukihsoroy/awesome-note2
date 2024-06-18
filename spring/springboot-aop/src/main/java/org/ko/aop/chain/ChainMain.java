package org.ko.aop.chain;

import java.util.Arrays;
import java.util.List;

public class ChainMain {
    static class ChainHandlerA extends ChainHandler {
        @Override
        protected void handlerProcess() {
            System.out.println("handler by chain a");
        }
    }

    static class ChainHandlerB extends ChainHandler {
        @Override
        protected void handlerProcess() {
            System.out.println("handler by chain b");
        }
    }

    static class ChainHandlerC extends ChainHandler {
        @Override
        protected void handlerProcess() {
            System.out.println("handler by chain c");
        }
    }

    public static void main(String[] args) {
        List<ChainHandler> handlers = Arrays.asList(
                new ChainHandlerA(),
                new ChainHandlerB(),
                new ChainHandlerC()
        );
        Chain chain = new Chain(handlers);
        chain.proceed();
    }

}
