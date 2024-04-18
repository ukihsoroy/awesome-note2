package org.ko.concurrency.example.singleton;

import org.ko.concurrency.annoations.NotRecommend;
import org.ko.concurrency.annoations.ThreadSafe;

/**
 * 懒汉模式
 * 单例实例在第一次使用的时候创建
 */
@ThreadSafe
@NotRecommend
public class SingletonExample3 {

    //私有构造函数
    private SingletonExample3() {}

    //单例对象
    private static SingletonExample3 instance = null;

    //静态的工厂方法来获取一个单例对象
    public static synchronized SingletonExample3 getInstance() {
        if (instance == null) {
            instance = new SingletonExample3();
        }
        return instance;
    }
}
