package com.exmaple;

import java.io.IOException;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class InterruptedExample {
    public static void main(String[] args) throws InterruptedException, IOException {
        Semaphore semaphore = new Semaphore(1);
        semaphore.acquire();
        Thread t1= new Thread(()->{
            while(true){
                try {
                    System.out.println("t1 start");
                    System.out.println("1 "+Thread.currentThread().isInterrupted());
                    semaphore.acquire();
                    System.out.println("2 "+Thread.currentThread().isInterrupted());
                    System.out.println("t1 wait after");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.out.println("3 "+Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                }
            }
        },"t1");

        t1.start();

        TimeUnit.SECONDS.sleep(1);
        System.out.println("4 "+t1.isInterrupted());
        t1.interrupt();
        System.out.println("5 "+t1.isInterrupted());
        System.in.read();

    }
}
