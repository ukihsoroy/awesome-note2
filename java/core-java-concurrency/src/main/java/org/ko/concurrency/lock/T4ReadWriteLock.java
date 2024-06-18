package org.ko.concurrency.lock;

import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 * 读-读能共存, 读-写不能共存, 写-写不能共存
 */
public class T4ReadWriteLock {

    public static void main(String[] args) {
        //线程数
        final int threadCount = 6;
        //创建线程池
        final ExecutorService exService = Executors.newFixedThreadPool(threadCount);
        final ScoreBoard scoreBoard = new ScoreBoard();
        //运行写线程
        exService.execute(new ScoreUpdateThread(scoreBoard));
        //运行读线程
        for (int i = 0; i < 5; i++) {
            exService.execute(new ScoreHealthThread(scoreBoard));
        }
        //线程执行结束后 停止线程池
        exService.shutdown();
    }
}

class ScoreBoard {

    /**
     * 是否更新
     */
    private boolean scoreUpdated = false;

    private int score = 0;

    String health = "不可用";

    //读写线程锁
    final ReentrantReadWriteLock rrwl = new ReentrantReadWriteLock();

    public String getMatchHealth() {
        //读线程锁
        rrwl.readLock().lock();
        //如果是要写入
        if (scoreUpdated) {
            //解除读线程锁
            rrwl.readLock().unlock();
            //开启写线程锁
            rrwl.writeLock().lock();
            try {
                //更新数据
                if (scoreUpdated) {
                    score = fetchScore();
                    scoreUpdated = false;
                }
                //写入完毕 开启读线程锁
                rrwl.readLock().lock();
            } finally {
                //结束写线程锁
                rrwl.writeLock().unlock();
            }
        }
        try {
            //在读线程锁中 允许多线程读取
            if (score % 2 == 0) {
                health = "Bad Score";
            } else {
                health = "Good Score";
            }
        } finally {
            //结束读线程锁
            rrwl.readLock().unlock();
        }
        return health;
    }

    public void updateScore() {
        try {
            //写线程互斥锁
            rrwl.writeLock().lock();
            scoreUpdated = true;
        } finally {
            //结束写线程锁
            rrwl.writeLock().unlock();
        }
    }

    private int fetchScore() {
        Calendar calender = Calendar.getInstance();
        return calender.get(Calendar.MILLISECOND);
    }
}

/**
 * 读取线程
 */
class ScoreHealthThread implements Runnable {

    private ScoreBoard scoreBoard;

    public ScoreHealthThread(ScoreBoard scoreTable) {
        this.scoreBoard = scoreTable;
    }

    @Override
    public void run() {
        for(int i= 0; i< 5; i++) {
            System.out.println("Match Health: "+ scoreBoard.getMatchHealth());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/**
 * 写入线程
 */
class ScoreUpdateThread implements Runnable {

    private ScoreBoard scoreBoard;

    public ScoreUpdateThread(ScoreBoard scoreTable) {
        this.scoreBoard = scoreTable;
    }

    @Override
    public void run() {
        for(int i= 0; i < 5; i++) {
            System.out.println("Score Updated.");
            scoreBoard.updateScore();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
