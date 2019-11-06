package com.exmaple;

import java.util.concurrent.TimeUnit;

public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for(;;);
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
