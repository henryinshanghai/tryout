package com.henry.tryout.async_program.via_future_in_JDK_02.workingMechanism_05;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinTask;

public class AsyncRun_comments_02 {

    @SuppressWarnings("serial")
    static final class AsyncRun { // extends ForkJoinTask<Void> implements Runnable, AsynchronousCompletionTask

        // 保存 创建的future对象 + 需要执行的行为
        CompletableFuture<Void> dep; Runnable fn;
        AsyncRun(CompletableFuture<Void> dep, Runnable fn) {
            this.dep = dep; this.fn = fn;
        }

        /* irrelevant codes */

        public void run() {
            CompletableFuture<Void> d; Runnable f;
            if ((d = dep) != null && (f = fn) != null) {
                dep = null; fn = null;
                // 5. 如果future的result为null，则：说明 任务还没有完成
//                if (d.result == null) {
//                    try {
//                        // 5.1 执行投递到线程池中的行为
//                        f.run();
//                        // 5.2 设置 future的结果为 null - 其他因为调用get()方法阻塞的线程就会得到返回值 null
//                        d.completeNull();
//                    } catch (Throwable ex) {
//                        d.completeThrowable(ex);
//                    }
//                }

                // 6. 弹出 当前future中、依赖当前结果的行为，并执行该行为
//                d.postComplete();
            }
        }
    }
}
