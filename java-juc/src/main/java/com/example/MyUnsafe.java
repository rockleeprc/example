package com.example;

import sun.misc.Unsafe;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

import java.lang.reflect.Field;

public class MyUnsafe {
    static Unsafe unsafe;
    static long stageOffset;
    private volatile long state = 0;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null); // 不能通过Unsafe.getUnsafe()获取，对类加载器进行了判断
            stageOffset = unsafe.objectFieldOffset(MyUnsafe.class.getDeclaredField("state"));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        final MyUnsafe t = new MyUnsafe();

        Thread t1 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "-" + unsafe.compareAndSwapInt(t, stageOffset, 0, 1));
            System.out.println(t.state);
        }, "t1");

        Thread t2 = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "-" + unsafe.compareAndSwapInt(t, stageOffset, 0, 1));
            System.out.println(t.state);
        }, "t2");

        t1.start();
        t2.start();

    }
}
