package leetCode.concurrence1117;

import explain.concurrent.aqs.Condition;
import explain.concurrent.aqs.Lock;
import explain.concurrent.aqs.rttl.ReentrantLock;

import java.util.concurrent.Semaphore;

class H2O {
    //    Semaphore h = new Semaphore(2);
//    Semaphore o = new Semaphore(0);
//    public H2O() {
//
//    }
//
//    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
//        h.acquire();
//        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
//        releaseHydrogen.run();
//        o.release();
//    }
//
//    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
//        o.acquire(2);
//        // releaseOxygen.run() outputs "O". Do not change or remove this line.
//        releaseOxygen.run();
//        h.release(2);
//    }
    Lock lock;
    Condition oc;
    Condition hc;

    public H2O() {
        lock = new ReentrantLock();
        oc = lock.newCondition();
        hc = lock.newCondition();
    }

    private int hn = 2;
    private int on = 1;

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        lock.lock();
        if (hn == 0) {
            hc.await();
        }
        releaseHydrogen.run();
        --hn;
        if (hn == 0 && on == 0) {
            hn = 2;
            on = 1;
        } else {
            oc.signal();
        }
        lock.unlock();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        lock.lock();
        if (on == 0) {
            oc.await();
        }
        releaseOxygen.run();
        on = 0;
        if (hn == 0) {
            hn = 2;
            on = 1;
        } else {
            hc.signal();
        }
        lock.unlock();
    }
}
