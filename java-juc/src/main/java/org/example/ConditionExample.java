package org.example;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        t2();
    }

    public static void t2() throws InterruptedException {
        Thread t = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("await()");
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }, "t1");
        t.start();
        TimeUnit.SECONDS.sleep(1);

        lock.lock();
        try {
            System.out.println("signal");
            condition.signal();
        } finally {
            lock.unlock();
        }
        System.out.println("ending");
    }

    public static void t1() throws InterruptedException {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("a1 condition await before");
                condition.await();
                System.out.println("a1 condition await after");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println("a2 condition signal before");
                condition.signal();
                System.out.println("a2 condition signal after");
            } finally {
                lock.unlock();
            }
        });


        t1.start();
        TimeUnit.SECONDS.sleep(1);
        t2.start();

        t1.join();
        t2.join();

        System.out.println("ending");
    }

}
