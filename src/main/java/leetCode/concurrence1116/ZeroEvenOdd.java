package leetCode.concurrence1116;

import explain.concurrent.aqs.sp.Semaphore;

import java.util.function.IntConsumer;

public class ZeroEvenOdd {
    private final int n;
    private volatile int i = 0;
    private final Semaphore zero = new Semaphore(1);
    private final Semaphore even = new Semaphore(0);
    private final Semaphore odd = new Semaphore(0);


    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            zero.acquire();
            if (i == n) {
                ++i;
                even.release();
                odd.release();
                return;
            }
            // System.out.print(0);
            printNumber.accept(0);
            ++i;
            if (i % 2 == 0) {
                // 偶数
                even.release();
            } else {
                // 奇数
                odd.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            even.acquire();
            if (i > n) {
                return;
            }
            // System.out.print(i);
            printNumber.accept(i);
            zero.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        while (true) {
            odd.acquire();
            if (i > n) {
                return;
            }
            // System.out.print(i);
            printNumber.accept(i);
            zero.release();
        }
    }

    public static void main(String[] args) {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(2);
    }
}
