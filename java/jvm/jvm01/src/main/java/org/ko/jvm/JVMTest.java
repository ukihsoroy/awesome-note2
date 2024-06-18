package org.ko.jvm;

import java.io.File;

/**
 * 数据, 指令, 控制
 */
public class JVMTest {

    //常量, 静态变量
    private final int i = 0;
    private static int k = 0;

    //成员变量
    private Object object = new Object();
    private int js = 0;

    //局部变量
    public void method1 () {
        int j = 0;
        int sum = i + j;
        Object target = object;
        long start = System.currentTimeMillis();
        method2();
        return;
        //正常
        //异常
    }

    public void method2 () {
        File file = new File("");
        //TODO

    }
}
