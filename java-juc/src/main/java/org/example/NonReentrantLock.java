package org.example;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

public class NonReentrantLock {

    private static class Sync extends AbstractQueuedSynchronizer {

        /**
         * state==0，没有线程获取锁
         * state==1，有线程持有锁，互斥
         *
         * @param arg
         * @return
         */
        @Override
        protected boolean tryAcquire(int arg) {
            assert arg == 1;
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            assert arg == 1;
            if (getState() == 0)
                throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        protected Condition newCondition() {
            return new ConditionObject();
        }

        protected boolean isHeldExclusively() {
            return getState() == 0;
        }
    }


    private static final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public boolean unlock() {
        return sync.release(1);
    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public static void main(String[] args) throws InterruptedException {
        NonReentrantLock lock = new NonReentrantLock();

        Thread t1 = new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " locked...");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " release...");
            }
        }, "t1");

        Thread t2 = new Thread(() -> {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " locked...");
            try {

            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName() + " release...");
            }
        }, "t2");

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }
}
