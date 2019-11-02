package com.exmaple;

import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProductConsumer {
    private static final Integer capacity = 5;

    private static final LinkedBlockingQueue queue = new LinkedBlockingQueue(capacity);


    public static void main(String[] args) throws InterruptedException, IOException {
        Producer producer = new Producer();
        Consumer consumer = new Consumer();

        Thread p1 = new Thread(producer);
        Thread c1 = new Thread(consumer);
        Thread c2 = new Thread(consumer);

        p1.start();
        c1.start();
        c2.start();

        p1.join();
        c1.join();
        c2.join();

        System.out.println("will read in wait");
    }

    private static class Producer implements Runnable {


        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (queue) {
                        while (queue.size() == capacity) {
                            System.out.println("producer wait");
                            queue.wait();
                        }
                        long product = System.currentTimeMillis();
                        queue.add(product);
                        System.out.println("product=" + product);
                        queue.notifyAll();
                    }
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static class Consumer implements Runnable {

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (queue) {
                        while (queue.size() == 0) {
                            System.out.println("consumer wait");
                            queue.wait();
                        }
                        System.out.println("consumer=" + queue.take());
                        queue.notifyAll();
                    }
                    TimeUnit.SECONDS.sleep(2);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
