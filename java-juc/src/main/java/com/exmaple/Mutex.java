package com.exmaple;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Mutex implements Lock, java.io.Serializable {

    // Our internal helper class
    private static class Sync extends AbstractQueuedSynchronizer {
        // Reports whether in locked state
        /* 当前锁是否被独占 */
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        // Acquires the lock if state is zero
        public boolean tryAcquire(int acquires) {
            assert acquires == 1; // Otherwise unused
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        // Releases the lock by setting state to zero
        protected boolean tryRelease(int releases) {
            assert releases == 1; // Otherwise unused
            if (getState() == 0) throw new IllegalMonitorStateException();
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }

        // Provides a Condition
        Condition newCondition() {
            return new ConditionObject();
        }

        // Deserializes properly
        private void readObject(ObjectInputStream s)
                throws IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0); // reset to unlocked state
        }
    }

    // The sync object does all the hard work. We just forward to it.
    private final Sync sync = new Sync();

    public void lock() {
        sync.acquire(1);
    }

    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    public void unlock() {
        sync.release(1);
    }

    public Condition newCondition() {
        return sync.newCondition();
    }

    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreads() {
        return sync.hasQueuedThreads();
    }

    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit)
            throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    public static void main(String[] args) throws InterruptedException {
        Mutex lock = new Mutex();
        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t2").start();

        TimeUnit.SECONDS.sleep(2);

        new Thread(() -> {
            lock.lock();
            try {
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t3").start();


    }
}
