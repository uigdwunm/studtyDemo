package explain.concurrent.aqs.rttl;

/**
 * Sync object for fair locks
 */
final class FairSync extends Sync {
    private static final long serialVersionUID = -3000897897090466540L;

    final void lock() {
        acquire(1);
    }

    /**
     * Fair version of tryAcquire.  Don't grant access unless
     * recursive call or no waiters or is first.
     */
    protected final boolean tryAcquire(int acquires) {
        final Thread current = Thread.currentThread();
        int c = getState();
        if (c == 0) {
            // 进入这里是当前没有线程持有锁
            if (hasQueuedPredecessors()) {
                // 当前有线程在排队，直接返回false
                // 因为是公平锁不能插队
                return false;
            }
            // 走到这说明没有线程排队，cas获取锁
            if (compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        } else if (current == getExclusiveOwnerThread()) {
            // 持有锁的是当前线程，那么这里可以重入
            int nextc = c + acquires;
            if (nextc < 0) {
                throw new Error("Maximum lock count exceeded");
            }
            setState(nextc);
            return true;
        }
        // 当前有其他线程正在持有锁，本次争抢失败
        return false;
    }
}
