package explain.concurrent.test;

import explain.concurrent.aqs.Condition;
import explain.concurrent.aqs.rttl.ReentrantLock;
import explain.concurrent.aqs.sp.Semaphore;

import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class AQSDemo {
    public static void main(String[] args) throws Exception {
        Semaphore semaphore = new Semaphore(19);
        semaphore.acquire();
        semaphore.release();

        AQSDemo aqsDemo = new AQSDemo();
        aqsDemo.reentrantLockTest();
    }

    private void semaphoreTest() throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        semaphore.acquire(2);
        semaphore.release();
    }

    private int num = 0;

    private void reentrantLockTest() throws InterruptedException {
        int threadNum = 2;
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();
        CyclicBarrier start = new CyclicBarrier(threadNum);
        CountDownLatch end = new CountDownLatch(threadNum);

        for (int i = 0; i < threadNum; i++) {
            new Thread(() -> {
                try {
                    start.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                reentrantLock.lock();
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                condition.signal();
                for (int j = 0; j < 1000; j++) {
                    ++num;
                }
                reentrantLock.unlock();
                end.countDown();
            }).start();
        }

        end.await();
        System.out.println(num);
    }
}
