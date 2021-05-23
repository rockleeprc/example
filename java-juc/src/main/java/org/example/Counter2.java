package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Counter2 {
    static volatile List<Object> list = new ArrayList<>();
    static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 start");
                if (list.size() != 5) {
                    try {
                        lock.wait();// 释放锁 让t1运行
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 ending");
                lock.notify();// 通知t1
            }
        }, "t2").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t1 start");
                for (int i = 0; i < 10; i++) {
                    list.add(i);
                    System.out.println(i);
                    if (i == 5) {
                        lock.notify(); // 通知t2

                        try {
                            lock.wait();// 释放锁 让t2运行
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t1 ending");
            }
        }, "t1").start();
    }
}
