package org.ko.concurrency.volatiles;

public class T1WithoutVolatile {

    /**
     *  -----线程中变量有缓存区 所以有可能出现值不一致的情况 会出现两种结果
     *  1) 打印出!=
     *  2) 直接退出程序
     */
//    private static boolean changed;
    /**
     * 排它锁
     * 使用volatile 设置后 变量线程可见 只会打印出!=
     * 使用volatile后
     * 1) 线程直接通过主内存中被读取或者写入
     * 2）使用volatile必须保证原子性
     * 3) i++这种不具备原子性
     */
    private volatile static boolean changed;

    public static void main(String[] args) {

        new Thread(() -> {
            for (;;) {
                if (changed == !changed) {
                    System.out.println("!=");
                    System.exit(0);
                }
            }
        }).start();

        new Thread(() -> {
            for (;;) {
                changed = !changed;
            }
        }).start();
    }
}
