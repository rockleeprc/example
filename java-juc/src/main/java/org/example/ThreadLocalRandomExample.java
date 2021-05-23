package org.example;


import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomExample {
    public static void main(String[] args) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        Thread t1 = new Thread(() -> {
            System.out.println(random.nextInt(5));
        }, "t1");
        Thread t2 = new Thread(() -> {
            System.out.println(random.nextInt(5));
        }, "t2");

        t1.start();
        t2.start();
    }
}
