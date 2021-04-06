package com.example;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * -XX:BiasedLockingStartupDelay=0 偏向锁延迟
 * -XX:-UseBiasedLocking 禁用偏向锁
 *
 * 1. 偏向锁（101）
 * 2. 无锁（001）
 * 3. 轻量级锁（000）
 */
public class BiasedLocking {
    public static void main(String[] args) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
        Dog dog = new Dog();
        ClassLayout classLayout = ClassLayout.parseInstance(dog);
        System.out.println(classLayout.toPrintable());
    }
}

class Dog {
}

