package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Counter3 {
    static volatile List<Object> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("t2 start");
            if (list.size() != 5) {
                try {
                    latch.await();// 等待t1 count down
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 ending");
        }, "t2").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            System.out.println("t1 start");
            for (int i = 0; i < 10; i++) {
                list.add(i);
                System.out.println(i);
                if (list.size() == 5) {
                    latch.countDown();
                }

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t1 ending");
        }, "t1").start();
    }
}
