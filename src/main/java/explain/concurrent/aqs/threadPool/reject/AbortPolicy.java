package explain.concurrent.aqs.threadPool.reject;

import explain.concurrent.aqs.threadPool.ThreadPoolExecutor;

import java.util.concurrent.RejectedExecutionException;

/**
 * A handler for rejected tasks that throws a
 * {@code RejectedExecutionException}.
 */
public class AbortPolicy implements RejectedExecutionHandler {
    /**
     * Creates an {@code AbortPolicy}.
     */
    public AbortPolicy() { }

    /**
     * Always throws RejectedExecutionException.
     *
     * @param r the runnable task requested to be executed
     * @param e the executor attempting to execute this task
     * @throws RejectedExecutionException always
     */
    public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        throw new RejectedExecutionException("Task " + r.toString() +
                " rejected from " +
                e.toString());
    }
}
