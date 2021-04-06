package com.example;

import java.util.ArrayList;
import java.util.List;
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
        List list = new ArrayList();
        list.add(1);
    }
}
