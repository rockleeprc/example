package org.example;

import java.util.concurrent.atomic.AtomicLong;

/*
int i = 10;
i = i++;

int temp=i; 读
i=i+1;  改
i=temp; 写

 */
public class AtomicExample {
    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong(0);
        System.out.println(atomicLong.getAndIncrement());

        /*
        atomicLong.compareAndSet();// while(true){}

        atomicLong.getAndIncrement();
        atomicLong.incrementAndGet();

        atomicLong.addAndGet();
        atomicLong.getAndAdd();
        */
        long result = atomicLong.updateAndGet(i->i*3);
        System.out.println(result);

        /*
            只能保护对象的引用是否修改，不能保证内容，比如数组
            AtomicReference
            ABA 问题
            AtomicStampedReference 版本号
            AtomicMarkableReference 判断状态 是否更改过

            包括数组内容
            AtomicIntegerArray

            性能：
            AtomicLong
            LongAdder
            LongAccumulator


        LongAdder
        LongAccumulator
         */


    }
}
