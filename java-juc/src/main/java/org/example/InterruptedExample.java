package org.example;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class InterruptedExample {
    public static void main(String[] args) throws InterruptedException, IOException {
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        Thread t1= new Thread(()->{
            while(true){
                try {
                    System.out.println("t1 start");
                    System.out.println("1 "+Thread.currentThread().isInterrupted());
                    semaphore.acquire();
                    System.out.println("2 "+Thread.currentThread().isInterrupted());
                    System.out.println("t1 wait after");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("3 "+Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
        },"t1");

        t1.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("4 "+t1.isInterrupted());
        t1.interrupt();
        System.out.println("5 "+t1.isInterrupted());
        System.in.read();

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
