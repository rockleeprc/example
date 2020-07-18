package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Counter1 {
    static volatile List<Object> list = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException, IOException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                list.add(i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                if (list.size() == 5) {
                    System.out.println("break size=" + list.size());
                    break;
                }
            }
        });

        t2.start();
        t1.start();

    }

}
