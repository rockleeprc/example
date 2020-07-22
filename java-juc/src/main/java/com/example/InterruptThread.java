package com.example;

import java.util.concurrent.TimeUnit;

public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        t1();
    }

    public static void t4() throws InterruptedException {
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

    /**
     * 不清除中断标志
     *
     * @throws InterruptedException
     */
    public static void t3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println("1 " + Thread.currentThread().isInterrupted());
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("2 " + Thread.currentThread().isInterrupted());
                    break;
                }
            }
        }, "t1");

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
        t1.join();
    }

    /**
     * 清除中断标志
     *
     * @throws InterruptedException
     */
    public static void t2() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                boolean flag = Thread.interrupted();
                System.out.println("1 "+flag);
                if (flag) {
                    System.out.println("2 " + Thread.interrupted());
                    break;
                }
            }
        }, "t1");

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();
        t1.join();
    }

    public static void t1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (; ; ) ;
        });

        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t1.interrupt();// 清除中断标志

//        Thread.currentThread().interrupt();

        System.out.println(t1.isInterrupted());//true
        System.out.println(t1.interrupted());// false
        System.out.println(Thread.interrupted());//false
        System.out.println(t1.isInterrupted());//true

        t1.join();
    }
}
