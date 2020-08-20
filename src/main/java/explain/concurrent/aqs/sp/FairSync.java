package explain.concurrent.aqs.sp;

/**
 * Fair version
 */
final class FairSync extends Sync {
    private static final long serialVersionUID = 2014338818796000944L;

    FairSync(int permits) {
        super(permits);
    }

    protected int tryAcquireShared(int acquires) {
        for (;;) {
            // 看看队列里有没有排队的
            if (hasQueuedPredecessors()) {
                return -1;
            }
            int available = getState();
            int remaining = available - acquires;
            if (remaining < 0) {
                // 余量不够，这里返回一个负数
                return remaining;
            }
            // 走到这说明余量够了，cas获取锁
            if (compareAndSetState(available, remaining)) {
                return remaining;
            }
        }
    }
}
