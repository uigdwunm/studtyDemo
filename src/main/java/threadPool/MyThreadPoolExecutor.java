package threadPool;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class MyThreadPoolExecutor extends AbstractExecutorService {
    private static final Logger logger = Logger.getGlobal();


    public static MyThreadPoolExecutor newFixedThreadPool(int capacity) {
        return new MyThreadPoolExecutor(
                new LinkedBlockingQueue<>(),
                Executors.defaultThreadFactory(),
                capacity,
                capacity,
                Long.MAX_VALUE
        );

    }
    public MyThreadPoolExecutor(
            BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory,
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime) {
        this(
            workQueue,
            threadFactory,
            corePoolSize,
            maximumPoolSize,
            keepAliveTime,
            runnable -> {
                throw new RuntimeException("负荷满载，无法再接收任务了");
            }
            );
//        this.workQueue = workQueue;
//        this.threadFactory = threadFactory;
//        this.corePoolSize = corePoolSize;
//        this.maximumPoolSize = maximumPoolSize;
//        this.keepAliveTime = keepAliveTime;
//        this.handler = runnable -> {
//            throw new RuntimeException("负荷满载，无法再接收任务了");
//        };
//        threadList = new LinkedList<>();
    }

    public MyThreadPoolExecutor(
            BlockingQueue<Runnable> workQueue,
            ThreadFactory threadFactory,
            int corePoolSize,
            int maximumPoolSize,
            long keepAliveTime,
            Consumer<Runnable> handler) {
        this.workQueue = workQueue;
        this.threadFactory = threadFactory;
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.handler = handler;
        this.threadList = new LinkedList<>();
        this.mainLock = new ReentrantLock();
    }

    private enum Status {
        RUNNING(-1),
        // 暂定开始进入终止流程
        SHUTDOWN(0),
        STOP(1),
        TIDYING(2),
        TERMINATED(3);

        Status(int val) {}
    }

    // 线程池状态
    private Status status = Status.RUNNING;

    // 现在有的线程数量
    private final AtomicInteger workerCount = new AtomicInteger();

    // 等待队列
    private final BlockingQueue<Runnable> workQueue;

    // 线程池工厂
    private final ThreadFactory threadFactory;

    // 允许的常驻线程数量
    private final int corePoolSize;

    // 最大线程数
    private final int maximumPoolSize;

    // 超时时间,(毫秒值)
    private final long keepAliveTime;

    // 拒绝策略（当队列无法容纳时的处理方案）
    private final Consumer<Runnable> handler;

    // 收集线程,用于关闭线程池
    private final LinkedList<Thread> threadList;

    private final ReentrantLock mainLock;

    /**
     * Description 终止线程池，但是当前已有的任务可以执行完
     *
     * @author zhaolaiyuan
     * @Date 2020/5/7 18:35
     **/
    @Override
    public void shutdown() {
        // 开始进入终止流程
        this.status = Status.SHUTDOWN;
    }

    /**
     * Description 直接终止，包括正在执行的任务
     *
     * @author zhaolaiyuan
     * @Date 2020/5/7 18:35
     **/
    @Override
    public List<Runnable> shutdownNow() {
        // 这个是调用线程的中断
        this.status = Status.STOP;

        for (Thread thread : this.threadList) {

        }
        return null;
    }

    /**
     * Description 是否终止了
     *
     * @author zhaolaiyuan
     * @Date 2020/5/7 18:34
     **/
    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void execute(Runnable command) {
        if (!this.status.equals(Status.RUNNING)) {
            throw new RuntimeException("已经开始终止的线程池，无法再接收任务");
        }
        // 新进来任务：
        if (workerCount.get() < corePoolSize) {
            // 1，核心线程数不够直接创建
            Thread thread = threadFactory.newThread(() -> runNewThread(command));
            threadList.add(thread);
            thread.start();
            workerCount.incrementAndGet();
            logger.info("创建核心线程");
        } else if (workQueue.offer(command)) {
            // 2，阻塞队列没满则添加,进来就说明添加成功
            logger.info("进入阻塞队列");

        } else if (workerCount.get() < maximumPoolSize){
            // 3，最大线程数还有可以创建
            Thread thread = threadFactory.newThread(() -> this.runNewThread(command));
            threadList.add(thread);
            thread.start();
            workerCount.incrementAndGet();
            logger.info("创建临时工线程线程");
        } else {
            // 4，拒绝策略
            handler.accept(command);
        }
    }

    private void runNewThread(Runnable task) {
        try {
            if (task == null) {
                task = this.getTask();
                if (task == null) {
                    // 这里虽然结束了但依然会触发finally
                    return;
                }
            }
            do {
                task.run();
            } while ((task = this.getTask()) != null);
        } catch (InterruptedException e) {
            // 线程获取锁时中断了
            logger.info("线程" + Thread.currentThread().getName() + "被中断辣");
        } finally {
            this.threadStop();
        }

    }

    private void threadStop() {
        // TODO 目前还不知道干点啥

    }

    private Runnable getTask() throws InterruptedException {
        // 判断剩余线程还够不够核心线程数
        if (corePoolSize < workerCount.get()) {
            // 一定时间获取不到任务就直接返回
            return workQueue.poll(keepAliveTime, TimeUnit.MILLISECONDS);
        } else {
            // 获取不到任务会一直等待，核心线程才能到这
            return workQueue.take();
        }
    }

}
