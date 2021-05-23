package org.example;

public class ThreadLocalExample {
    static ThreadLocal<String> local1 = new ThreadLocal<>();
    static ThreadLocal<String> local2 = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            local1.set("A");
            local2.set("B");
            local1.set("C");
        },"t1");

        t.start();
        while (Thread.activeCount() > 0) ;
    }

    /**
     * 无法复现npe
     */
    public static void t3() throws InterruptedException {
        ThreadLocal<Long> threadLocal = new ThreadLocal<>();
        new Thread(() -> {
            threadLocal.set(1L);
            System.out.println(threadLocal.get());
        }).start();
        // 目的就是为了让子线程先运行完
        Thread.sleep(100);
        System.out.println(threadLocal.get());
    }

    /**
     * ThreadLocal内存泄漏
     */
    public static void t2() {
        // TODO 断点看此时的threadLocals.Entry数组刚设置的referent是指向Local的，referent就是Entry中的key只是被WeakReference包装了一下
        Thread mainThread = Thread.currentThread();
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("当前线程名称：" + Thread.currentThread().getName());
        // threadLocal.remove();// 建议手动清除
        /*
            1、传递的入参是null，方法内new了，也不会影响到外面null的对象，还是null的
            2、传递的入参不是null，方法内=null了，也不会影响到外面不是null的对象
            3、传递的入参不null，传递到线程中，线程运行中，外面把入参设置为null，线程内继续不是null
         */
        threadLocal = null;//断开强引用，即断开threadLocal与referent的关联，但Entry中此时的referent还是指向threadLocal的，当引用传递设置为null时无法影响传递内的结果
        System.gc();
        /*
            这时Entry中referent是null了，被GC掉了，因为Entry和key的关系是WeakReference，并且在没有其他强引用的情况下就被回收掉了
            如果这里不采用WeakReference，即使threadLocal=null，那么也不会回收Entry的key，因为Entry和key是强关联
            但是这里仅能做到回收key不能回收value，如果这个线程运行时间非常长，即使referent GC了，value持续不清空，就有内存溢出的风险
            彻底回收最好调用remove 即：threadLocal.remove(); remove相当于把ThreadLocalMap里的这个元素干掉了，并没有把自己干掉
         */
        System.out.println(threadLocal);
    }

    public static void t1() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
//     ThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

        Thread t1 = new Thread(() -> {
            System.out.println("set local A");
            threadLocal.set("A");
            System.out.println("t1 get thead local:" + threadLocal.get());
            threadLocal.remove();
            System.out.println("t1 remove after get thead local:" + threadLocal.get());

        });
        Thread t2 = new Thread(() -> {
            System.out.println("set local B");
            threadLocal.set("B");
            System.out.println("t2 get thead local:" + threadLocal.get());
            threadLocal.remove();
            System.out.println("t2 remove after get thead local:" + threadLocal.get());
        });

        t1.start();
        t2.start();
    }
}
