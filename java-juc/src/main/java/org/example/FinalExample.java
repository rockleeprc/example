package org.example;

public class FinalExample {
    // bipush 直接从栈空间获取
    final static int A = 10;
    // lcd 32768 读取常量池数据到栈空间
    final static int B = Short.MAX_VALUE+1;
    // getstatic 通过FinalExample类访问B
    static int c = 10;
    static int d = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println(FinalExample.A);
        System.out.println(FinalExample.B);
        System.out.println(FinalExample.c);
        System.out.println(FinalExample.d);
    }
}
