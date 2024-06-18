package org.ko.concurrency.example.singleton;

import org.ko.concurrency.annoations.NotThreadSafe;

/**
 * 懒汉模式 --》双重同步锁单例模式
 * 单例实例在第一次使用的时候创建
 */
@NotThreadSafe
public class SingletonExample4 {

    //私有构造函数
    private SingletonExample4() {}

    /**
     * instance = new SingletonExample4();
     * 1. memory = allocate() 分配对象的内存空间
     * 2. createInstance() 初始化对象
     * 3. instance = memory 设置instance指向刚分配的内存
     *
     * -------------------
     * JVM和cpu优化，发生了指令重排，变成了 1 3 2
     * 1. memory = allocate() 分配对象的内存空间
     * 3. instance = memory 设置instance指向刚分配的内存
     * 2. createInstance() 初始化对象
     */
    //单例对象
    private static SingletonExample4 instance = null;

    //静态的工厂方法来获取一个单例对象
    public static SingletonExample4 getInstance() {
        if (instance == null) { //双重检测机制
            synchronized (SingletonExample4.class) { //同步锁
                if (instance == null) {
                    instance = new SingletonExample4(); // A - 3
                }
            }
        }
        return instance;
    }
}
