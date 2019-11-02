package com.exmaple;

public class InterruptThread {
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(()->{
            for(;;);
        });

        t1.start();
        t1.interrupt();

        Thread.currentThread().interrupt();

        System.out.println(t1.isInterrupted());//true
        System.out.println(t1.interrupted());// false
        System.out.println(Thread.interrupted());//false
        System.out.println(t1.isInterrupted());//true

        t1.join();
    }
}
