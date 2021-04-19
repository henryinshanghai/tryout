package com.henry.tryout.async_program.via_future_in_JDK_02.workingMechanism_05;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.function.Supplier;

public class supplyAsync_comments_04 {

    private static final Executor ASYNC_POOL = ForkJoinPool.commonPool();

    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier) {
        // note: 提交到线程池中的是 AsyncSupply类型的任务
        return asyncSupplyStage(ASYNC_POOL, supplier);
    }

    static <U> CompletableFuture<U> asyncSupplyStage(Executor e,
                                                     Supplier<U> f) {
        if (f == null) throw new NullPointerException();
        CompletableFuture<U> d = new CompletableFuture<U>();
//        e.execute(new CompletableFuture.AsyncSupply<U>(d, f));
        return d;
    }

    @SuppressWarnings("serial")
    static final class AsyncSupply<T>  { // extends ForkJoinTask<Void>  implements Runnable, AsynchronousCompletionTask
        CompletableFuture<T> dep; Supplier<? extends T> fn;
        AsyncSupply(CompletableFuture<T> dep, Supplier<? extends T> fn) {

            // future对象dep + 任务本身fn
            this.dep = dep; this.fn = fn;
        }

        /* irrelevant codes */

        public void run() {
            CompletableFuture<T> d; Supplier<? extends T> f;
            if ((d = dep) != null && (f = fn) != null) {
                dep = null; fn = null;
                // 如果future对象的result属性的值为 null，则：说明任务还没有被完成
//                if (d.result == null) {
//                    try {
                          // f.get() 用于 任务/行为f的执行结果
                          // 把行为的执行结果 设置到 future对象中去
//                        d.completeValue(f.get());
//                    } catch (Throwable ex) {
//                        d.completeThrowable(ex);
//                    }
//                }
                  // 弹出当前future对象中、依赖于当前结果的行为 并 加以执行
//                d.postComplete();
            }
        }
    }
}
/*
supplyAsync 相比于 runAsync
1 supplyAsync 中，表示行为/任务的对象是 supplier实例；
    而 runAsync 中，表示任务的对象是 runnable实例；

2 supplyAsync()方法是有返回值的 - 这个返回值会被设置到future对象中。
    通过future对象的get()方法就能够得到 设置到 future对象中的返回值
 */