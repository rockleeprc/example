package com.example;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class AlternatePrinting {
    private static final ReentrantLock lock = new ReentrantLock();
    private static final Object monitor = new Object();
    private static final ThreadLocal<String> LOCAL = new ThreadLocal<>();
    private static Thread t1;
    private static Thread t2;
    private static int i = 3;
    static final WeakReference<Person> weak = new WeakReference<Person>(new Person("liyan"));

    public static void main(String[] args) throws Exception {

    }


    private static class Person {

        public Person(String name) {
            this.name = name;
        }

        String name;

        @Override
        protected void finalize() throws Throwable {
            System.out.println("GC");
        }
    }

    public static void t4() {
        Condition t1Condition = lock.newCondition();
        Condition t2Condition = lock.newCondition();

        char[] c1 = "abcd".toCharArray();
        char[] c2 = "1234".toCharArray();

        t1 = new Thread(() -> {

            lock.lock();
            try {
                for (int i = 0; i < c1.length; i++) {
                    System.out.print(c1[i]);
                    t1Condition.await();
                    t2Condition.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1");
        t1.start();

        t2 = new Thread(() -> {
            lock.lock();
            try {
                for (int i = 0; i < c2.length; i++) {
                    System.out.print(c2[i]);
                    t1Condition.signal();
                    t2Condition.await();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2");


        t2.start();
    }

    public static void t3() {
        char[] c1 = "abcd".toCharArray();
        char[] c2 = "1234".toCharArray();

        t1 = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < c1.length; i++) {
                    System.out.print(c1[i]);
                    monitor.notify();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");

        t2 = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < c2.length; i++) {
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.print(c2[i]);
                    monitor.notify();
                }
            }
        }, "t2");

        t1.start();
        t2.start();
    }

    public static void t2() {
        char[] c1 = "abcd".toCharArray();
        char[] c2 = "1234".toCharArray();

        t1 = new Thread(() -> {
            for (int i = 0; i < c1.length; i++) {
                System.out.print(c1[i]);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {
            for (int i = 0; i < c2.length; i++) {
                LockSupport.park();
                System.out.print(c2[i]);
                LockSupport.unpark(t1);
            }
        }, "t2");

        t1.start();
        t2.start();
    }

    public static void t1() throws Exception {
        Thread t1 = new Thread(() -> {
            lock.lock();
            try {
                while (true) {
                }
            } finally {
                lock.unlock();
            }
        }, "t1");
        t1.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("t2");

        Thread t2 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(".");
            } finally {
                lock.unlock();
            }
        }, "t2");
        t2.start();


        TimeUnit.SECONDS.sleep(1);
        System.out.println("t3");
        Thread t3 = new Thread(() -> {
            lock.lock();
            try {
                System.out.println(".");
            } finally {
                lock.unlock();
            }
        }, "t3");
        t3.start();

        System.in.read();
    }
}
