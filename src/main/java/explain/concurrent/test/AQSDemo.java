package explain.concurrent.test;

import explain.concurrent.aqs.Condition;
import explain.concurrent.aqs.rttl.ReentrantLock;

import java.util.LinkedList;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class AQSDemo {
    public static void main(String[] args) throws Exception {

        AQSDemo aqsDemo = new AQSDemo();
        aqsDemo.reentrantLockTest();
    }


    private int num = 0;

    private void reentrantLockTest() throws InterruptedException {
        LinkedList<Character> list = new LinkedList<>();
        char c = 'a';
        list.push(c);
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
