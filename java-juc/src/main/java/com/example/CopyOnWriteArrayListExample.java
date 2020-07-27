package com.example;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class CopyOnWriteArrayListExample {
    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        list.add("A");
        list.add("b");
        list.add("c");

        Thread t = new Thread(() -> {
            list.remove(0);
            list.remove(0);
        }, "t1");

        Iterator<String> iterator = list.iterator();

        t.start();
        t.join();

        while (iterator.hasNext()) {
            String e = iterator.next();
            System.out.println(e);
        }

        System.out.println(list.size());
    }
}
