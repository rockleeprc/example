package com.example;

/**
 * 斐波那契数列
 * 算法复杂度
 */
public class Fib {
    public static void main(String[] args) {
//        System.out.println(fib1(30));
        System.out.println(fib2(164));
    }

    private static int fib3(int n) {
        if (n <= 1) return n;
        int first = 0;
        int second = 1;

        for (int i = 0; i < n - 1; i++) {
            second = first + second;
            first = second - first;
        }
        return second;
    }

    private static int fib2(int n) {
        if (n <= 1) return n;
        int first = 0;
        int second = 1;

        for (int i = 0; i < n - 1; i++) {
            // 0+1
            int sum = first + second;// index[0]+index[1] = next index[2]
            first = second;// index[0] = index[1] 前一个的second是当前的first
            second = sum;//
        }
        return second;
    }

    /**
     * 0 1 1 2 3 5 8
     * 0 1 2 3 4 5 6
     *
     * @param n
     * @return
     */
    private static int fib1(int n) {
        if (n <= 1) {
            return n;
        }
//        System.out.println("fib"+n);
        return fib1(n - 1) + fib1(n - 2);
    }
}