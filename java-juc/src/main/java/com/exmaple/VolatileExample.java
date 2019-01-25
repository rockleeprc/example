package com.exmaple;

import java.util.concurrent.TimeUnit;

public class VolatileExample {
    public static void main(String[] args) {
        Task task = new Task();
        new Thread(task).start();

        while (true) {
            if (task.isFlag()) {
                System.out.println("===============");
                break;
            }
        }
    }
}


class Task implements Runnable {
    /*
    1、volatile 不具备互斥性
    2、volatile 不保证变量的原子性
     */
    private volatile boolean flag = false;

    public void run() {

        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;
        System.out.println("flag=" + flag);
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
