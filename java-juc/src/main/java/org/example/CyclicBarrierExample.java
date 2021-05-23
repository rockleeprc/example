package org.example;

import java.util.concurrent.*;

public class CyclicBarrierExample {
    private static final ThreadPoolExecutor tpe;

    static {
        int core = Runtime.getRuntime().availableProcessors();
        tpe = new ThreadPoolExecutor(core, core, 0, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(10));
    }

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cb = new CyclicBarrier(3);
        tpe.execute(() -> {
            try {
                System.out.println(cb.await());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });
        tpe.execute(() -> {
            try {
                System.out.println(cb.await());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName());
        });
        tpe.shutdown();
        System.out.println(cb.await());

        System.out.println("ending");
    }
}
