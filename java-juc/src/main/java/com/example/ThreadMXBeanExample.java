package com.example;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;
import java.util.concurrent.locks.LockSupport;

public class ThreadMXBeanExample {
    public static void main(String[] args) {
        new Thread(() -> {
            while (true) ;
        }, "t1").start();
        new Thread(() -> LockSupport.park(), "t2").start();

        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        long[] threadIds = threadMXBean.getAllThreadIds();
        System.out.println(Arrays.toString(threadIds));

        ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(threadIds);
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println(threadInfo.getThreadId() + " " + threadInfo.getThreadName() + " " + threadInfo.getThreadState());
        }
    }
}
