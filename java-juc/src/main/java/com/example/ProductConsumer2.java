package com.example;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ProductConsumer2 {

    private static final ReentrantLock lock = new ReentrantLock();
    private static Condition full = lock.newCondition();
    private static Condition empty = lock.newCondition();

    private static int capcity = 5;
    private static final LinkedBlockingQueue<Long> queue = new LinkedBlockingQueue<>(capcity);

    public static void main(String[] args) throws InterruptedException {
        CP cp = new CP();
        Thread p1 = new Thread(new Producer(cp));
        Thread c1 = new Thread(new Consumer(cp));

        p1.start();
        c1.start();

        p1.join();
        c1.join();

        System.out.println("ending...");
    }


    private static class Producer implements Runnable {
        private CP cp;

        public Producer(CP cp) {
            this.cp = cp;
        }

        @Override
        public void run() {
            while (true) {
                cp.produce();
            }
        }
    }

    private static class Consumer implements Runnable {

        private CP cp;

        public Consumer(CP cp) {
            this.cp = cp;
        }

        @Override
        public void run() {
            while (true) {
                cp.consumer();
            }
        }
    }

    private static class CP {

        public void produce() {
            lock.lock();
            try {
                while (queue.size() == capcity) {
                    full.await();
                }
                long product = System.currentTimeMillis();
                System.out.println("P:" + product);
                queue.put(product);
                empty.signal();

                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void consumer() {
            lock.lock();
            try {
                while (queue.size() == 0) {
                    empty.wait();
                }

                Long product = queue.take();
                System.out.println("C:" + product);
                full.signal();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
