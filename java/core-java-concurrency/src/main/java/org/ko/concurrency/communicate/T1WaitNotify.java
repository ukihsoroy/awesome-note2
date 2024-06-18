package org.ko.concurrency.communicate;

public class T1WaitNotify {

    private volatile int val = 1;

    private synchronized void printAndIncrease() {
        System.out.println(Thread.currentThread().getName() +" prints " + val);
        val++;
    }

    // print 1,2,3 7,8,9
    public class PrinterA implements Runnable {
        @Override
        public void run() {
            //3) A线程先输出1, 2, 3
            while (val <= 3) {
                printAndIncrease();
            }

            //4) A线程等待获取锁
            // print 1,2,3 then notify printerB
            //6) A线程获得锁
            synchronized (T1WaitNotify.this) {
                System.out.println("PrinterA printed 1,2,3; notify PrinterB");
                T1WaitNotify.this.notify();
            }

            try {
                //8 A线程持有锁
                while (val <= 6) {
                    synchronized (T1WaitNotify.this) {
                        System.out.println("wait in printerA");
                        //10 A线程进入等待
                        T1WaitNotify.this.wait();
                    }
                }
                System.out.println("wait end printerA");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //12) A线程输出7, 8, 9
            while (val <= 9) {
                printAndIncrease();
            }
            System.out.println("PrinterA exits");
        }
    }

    // print 4,5,6 after printA print 1,2,3
    public class PrinterB implements Runnable {
        @Override
        public void run() {
            //1) 首先启动线程进入B B线程持有锁
            while (val < 3) {
                synchronized (T1WaitNotify.this) {
                    try {
                        System.out.println("printerB wait for printerA printed 1,2,3");
                        //2) B线程进入等待
                        T1WaitNotify.this.wait();
                        System.out.println("printerB waited for printerA printed 1,2,3");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                //5) B线程释放锁
            }
            //7) B线程输出4, 5, 6
            while (val <= 6) {
                printAndIncrease();
            }

            System.out.println("notify in printerB");
            //9) B线程等待获取锁
            synchronized (T1WaitNotify.this) {
                T1WaitNotify.this.notify();
            }
            //11) B线程推出
            System.out.println("notify end printerB");
            System.out.println("PrinterB exits.");
        }
    }

    public static void main(String[] args) {
        T1WaitNotify demo = new T1WaitNotify();
        demo.doPrint();
    }

    private void doPrint() {
        PrinterA pa = new PrinterA();
        PrinterB pb = new PrinterB();
        Thread a = new Thread(pa);
        a.setName("printerA");
        Thread b = new Thread(pb);
        b.setName("printerB");
        // 必须让b线程先执行，否则b线程有可能得不到锁，执行不了wait，而a线程一直持有锁，会先notify了
        b.start();
        a.start();
    }
}
