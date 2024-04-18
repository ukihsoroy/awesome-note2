package org.ko.concurrency.example.singleton;

import org.ko.concurrency.annoations.ThreadSafe;

/**
 * 饿汉模式
 * 单例实例在类装载时创建
 * 如果类加载处理比较多，会比较慢影响性能
 * 如果未被使用会造成资源浪费
 */
@ThreadSafe
public class SingletonExample6 {

    //私有构造函数
    private SingletonExample6() {}

    //单例对象
    private static SingletonExample6 instance = null;

    static {
        instance = new SingletonExample6();
    }


    //静态的工厂方法来获取一个单例对象
    public static SingletonExample6 getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(getInstance().hashCode());
        System.out.println(getInstance().hashCode());
    }
}
