package org.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportExample {

    public void park() {
        LockSupport.park(this);
    }

    public static void main(String[] args) throws InterruptedException {
    t1();
    }

    /**
     * park(对象)
     *
     * @throws InterruptedException
     */
    public static void t3() throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        Thread t = new Thread(() -> {
            System.out.println("park");
            LockSupport.park(mainThread); // jstack 可以看到park的线程 与不使用LockSupport.park()一样
        });
        t.start();
        TimeUnit.SECONDS.sleep(Long.MAX_VALUE);
        System.out.println("unpark");
        LockSupport.unpark(mainThread);

        System.out.println("ending");

        // jstack 能看到结果：- parking to wait for  <0x000000076b4804e0> (a com.example.LockSupportExample)
        LockSupportExample lock = new LockSupportExample();
        lock.park();
    }

    /**
     * 先调用unpark，在调用park
     *
     * @throws InterruptedException
     */
    public static void t2() throws InterruptedException {
        Thread mainThread = Thread.currentThread();
        System.out.println("unpark");
        LockSupport.unpark(mainThread);
        TimeUnit.SECONDS.sleep(1);
        System.out.println("park");
        LockSupport.park(); // 先执行unpark后，马上获得许可
        System.out.println("ending");
    }

    /**
     * 使用interrupt打断park
     *
     * @throws InterruptedException
     */
    public static void t1() throws InterruptedException {
        Thread t = new Thread(() -> {
            System.out.println("start park");
            while (!Thread.currentThread().isInterrupted()) {
                LockSupport.park();
            }
            System.out.println("end park");
        });

        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt(); // main 线程中打断t中的park()
        System.out.println("t1.interrupt in main thread");
    }
}
