package org.example;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class WeakReferenceExample {

    private static final int _4MB_LENGTH = 1024 * 1024 * 4;

    public static void main(String[] args) {
        // -Xmx20M -XX:+PrintGCDetails -verbose:gc
        referenceQueue();
    }

    public static void referenceQueue() {
        List<SoftReference<byte[]>> list = new ArrayList<>();
        ReferenceQueue<byte[]> queue = new ReferenceQueue();
        for (int i = 0; i <= 5; i++) {
            list.add(new SoftReference<>(new byte[_4MB_LENGTH], queue));
            for (SoftReference ref : list) {
                System.out.print(ref.get() + "|");
            }
            System.out.println();
        }
        System.out.println("list.size=" + list.size());
        Reference<byte[]> temp = (Reference<byte[]>) queue.poll();
        while (temp != null) {
            list.remove(temp);
            temp = (Reference<byte[]>) queue.poll();
        }
        for (SoftReference<byte[]> ref : list) {
            System.out.println("ref=" + ref + ",ref.get()=" + ref.get() + " | ");
        }
    }

    public static void strong() {
        List<byte[]> list = new ArrayList<>();

        for (int i = 0; i <= 5; i++) {
            list.add(new byte[_4MB_LENGTH]);
            System.out.println("loop i=" + i);
        }

    }

    public static void soft() {
        List<SoftReference<byte[]>> list = new ArrayList<>();

        for (int i = 0; i <= 5; i++) {
            list.add(new SoftReference<>(new byte[_4MB_LENGTH]));
            for (SoftReference ref : list) {
                System.out.print(ref.get() + "|");
            }
            System.out.println();
        }
        System.out.println("list.size=" + list.size());
        for (SoftReference<byte[]> ref : list) {
            System.out.println("ref=" + ref + ",ref.get()=" + ref.get() + " | ");
        }
    }

    public static void weak() {
        List<WeakReference<byte[]>> list = new ArrayList<>();

        for (int i = 0; i <= 5; i++) {
            list.add(new WeakReference<>(new byte[_4MB_LENGTH]));
            for (WeakReference ref : list) {
                System.out.print(ref.get() + "|");
            }
            System.out.println();
        }
        System.out.println("list.size=" + list.size());
        for (WeakReference<byte[]> ref : list) {
            System.out.println("ref=" + ref + ",ref.get()=" + ref.get() + " | ");
        }
    }

}

