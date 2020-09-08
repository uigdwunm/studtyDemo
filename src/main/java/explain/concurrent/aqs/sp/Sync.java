package explain.concurrent.aqs.sp;

import explain.concurrent.aqs.AbstractQueuedSynchronizer;

/**
 * Synchronization implementation for semaphore.  Uses AQS state
 * to represent permits. Subclassed into fair and nonfair
 * versions.
 */
abstract class Sync extends AbstractQueuedSynchronizer {
    private static final long serialVersionUID = 1192457210091910933L;

    Sync(int permits) {
        setState(permits);
    }

    final int getPermits() {
        return getState();
    }

    final int nonfairTryAcquireShared(int acquires) {
        for (;;) {
            // 获取当前的state值
            int available = getState();
            // state值减去传入的acquires
            int remaining = available - acquires;
            if (remaining < 0) {
                // 最终结果小于0，返回
                return remaining;
            }
            // 最终结果大于0，cas修改state值
            if (compareAndSetState(available, remaining)) {
                // 成功了返回
                return remaining;
            }
        }
    }

    protected final boolean tryReleaseShared(int releases) {
        for (;;) {
            // 获取当前状态
            int current = getState();
            // 加起来
            int next = current + releases;
            if (next < current) { // overflow
                // 结果变小了, 报错
                throw new Error("Maximum permit count exceeded");
            }
            if (compareAndSetState(current, next)) {
                // 成功
                return true;
            }
        }
    }

    final void reducePermits(int reductions) {
        for (;;) {
            int current = getState();
            int next = current - reductions;
            if (next > current) // underflow
                throw new Error("Permit count underflow");
            if (compareAndSetState(current, next)) {
                return;
            }
        }
    }

    final int drainPermits() {
        for (;;) {
            int current = getState();
            if (current == 0 || compareAndSetState(current, 0)) {
                return current;
            }
        }
    }
}
