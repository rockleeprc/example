package com.exmaple;

/*
int i = 10;
i = i++;

int temp=i; 读
i=i+1;  改
i=temp; 写

 */
public class AtomicExample {
    public static void main(String[] args) {
        int i=10;
        int j=i;
        i=20;
        System.out.println(j);
    }
}
