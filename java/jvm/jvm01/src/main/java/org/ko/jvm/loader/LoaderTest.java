package org.ko.jvm.loader;

public class LoaderTest {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader classLoader = ClassLoaderTests.class.getClassLoader();
        System.out.println(classLoader);

        //使用ClassLoader.loadClass()来加载类，不会执行初始化块
        classLoader.loadClass("org.ko.jvm.loader.Test");

        System.out.println("---");

        //使用Class.forName()来加载类，默认会执行初始化块
        Class.forName("org.ko.jvm.loader.Test");

        System.out.println("---");

        //使用Class.forName()来加载类，并指定ClassLoader，初始化时不执行静态块
        Class.forName("org.ko.jvm.loader.Test", false, classLoader);
    }

}
class Test{
    static {
        System.out.println("静态初始化块执行了。");
    }
}


