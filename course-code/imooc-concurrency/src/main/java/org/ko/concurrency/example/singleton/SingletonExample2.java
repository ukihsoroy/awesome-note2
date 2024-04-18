package org.ko.concurrency.example.singleton;

import org.ko.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时创建
 * 如果类加载处理比较多，会比较慢影响性能
 * 如果未被使用会造成资源浪费
 */
@ThreadSafe
public class SingletonExample2 {

    //私有构造函数
    private SingletonExample2() {}

    //单例对象
    private static SingletonExample2 instance = new SingletonExample2();

    //静态的工厂方法来获取一个单例对象
    public static SingletonExample2 getInstance() {
        return instance;
    }
}
