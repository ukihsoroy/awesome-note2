package org.ko.power;

import org.junit.jupiter.api.Test;

public class CallFunctionTests {

    @Test
    public void test1() {
        sum(1, 2);
        sum(1, 2, () -> System.out.println("client implement."));
    }

    public int sum(int a, int b) {
        return a + b;
    }

    public int sum(int a, int b, Log log) {
        log.call();
        return a + b;
    }

    public interface Log {
        void call();
    }

}
