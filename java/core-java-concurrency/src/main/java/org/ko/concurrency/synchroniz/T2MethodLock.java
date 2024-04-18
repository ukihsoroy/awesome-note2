package org.ko.concurrency.synchroniz;

import java.util.Date;

public class T2MethodLock {


    public static void main (String[] args) {

        Teacher teacher = new Teacher();
        Student s1 = new Student(teacher);
        Student s2 = new Student(teacher);
        s1.start();
        s2.start();
    }

}

class Teacher {

    /**
     * 方法同步锁 线程在调用方法时排队
     * @param name
     * @throws InterruptedException
     */
    public synchronized void say (String name) throws InterruptedException{
        for (int i = 0; i < 5; i++) {
            Thread.sleep(1000);
            System.out.println(name + "," + i + new Date().toLocaleString());
        }
    }

}

class Student extends Thread {

    private Teacher teacher;

    public Student (Teacher teacher) {
        this.teacher = teacher;
    }

    public void run() {
        try {
            teacher.say(Thread.currentThread().getName());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}