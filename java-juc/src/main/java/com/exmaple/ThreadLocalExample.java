package com.exmaple;

public class ThreadLocalExample {

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            System.out.println("set local A");
            threadLocal.set("A");
            System.out.println("t1 get thead local:"+threadLocal.get());
            threadLocal.remove();
            System.out.println("t1 remove after get thead local:"+threadLocal.get());

        });
        Thread t2 = new Thread(()->{
            System.out.println("set local B");
            threadLocal.set("B");
            System.out.println("t2 get thead local:"+threadLocal.get());
            threadLocal.remove();
            System.out.println("t2 remove after get thead local:"+threadLocal.get());
        });

        t1.start();
        t2.start();
    }
}
