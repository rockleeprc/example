package com.exmaple;

import java.util.concurrent.TimeUnit;

public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
t1();
    }

    /**
     * 不清除中断标志
     *
     * @throws InterruptedException
     */
    public static void t3() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            while (true) {
                System.out.println(Thread.currentThread().isInterrupted());
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().isInterrupted());
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
                System.out.println(flag);
                if (flag) {
                    System.out.println(flag + "--" + Thread.interrupted());
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

        Thread.currentThread().interrupt();

        System.out.println(t1.isInterrupted());//true
        System.out.println(t1.interrupted());// false
        System.out.println(Thread.interrupted());//false
        System.out.println(t1.isInterrupted());//true

        t1.join();
    }
}
