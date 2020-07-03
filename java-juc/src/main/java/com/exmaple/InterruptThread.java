package com.exmaple;

import java.util.concurrent.TimeUnit;

public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Object obj = new Object();
        Thread t1 = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    // wait抛异常后清除中断标记
                    Thread.currentThread().interrupt();// 设置中断标记
                    System.out.println("t1 " + Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
                for (; ; ) ; // 保持线程继续运行
            }
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
        System.out.println("main " + t1.isInterrupted());
    }

    public static void t1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (; ; ) ;
        });

        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t1.interrupt();// 清除中断标志

        Thread.currentThread().interrupt();

        System.out.println(t1.isInterrupted());//true
        System.out.println(t1.interrupted());// false
        System.out.println(Thread.interrupted());//false
        System.out.println(t1.isInterrupted());//true

        t1.join();
    }
}
