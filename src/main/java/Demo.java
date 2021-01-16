import com.sun.jmx.remote.internal.ArrayQueue;
import explain.map.HashMap;
import org.openjdk.jol.info.ClassLayout;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Description TODO
 *
 * @author zhaolaiyuan
 * @version 1.0
 * @Date 2019年2019/6/28 14:10
 **/
public class Demo {
    // 控制交替打印的信号量
    static Semaphore sA = new Semaphore(2);
    static Semaphore sB = new Semaphore(1);

    // A打印线程，负责打印A
    static class PrintA extends Thread {
        public void run() {
            while (true) {
                try {
                    sA.acquire(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                print('A');
                sA.release();
                sB.release();
            }
        }
    }
    // B打印线程，循环打印B
    static class PrintB extends Thread {
        public void run() {
            while (true) {
                try {
                    sB.acquire(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                print('B');
                sA.release();
                sB.release();
            }

        }
    }

    // 负责打印字符
    public static void print(char c) {
        System.out.println(c);
    }

    public static void main(String[] args) {
        List<Thread> threads = new ArrayList<>(100);
        // 准备50个B打印线程
        for (int i = 0; i < 50; ++i) {
            threads.add(new PrintA());
        }
        // 准备50个B打印线程
        for (int i = 0; i < 50; ++i) {
            threads.add(new PrintB());
        }

        // 所有打印线程启动
        for (Thread printer : threads) {
            printer.start();
        }
    }
}
