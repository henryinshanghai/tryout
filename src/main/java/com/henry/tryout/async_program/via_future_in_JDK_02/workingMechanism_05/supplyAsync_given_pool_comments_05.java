package com.henry.tryout.async_program.via_future_in_JDK_02.workingMechanism_05;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Supplier;

public class supplyAsync_given_pool_comments_05 {
    public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier,
                                                       Executor executor) {
        return asyncSupplyStage(screenExecutor(executor), supplier);
    }

    private static <U> CompletableFuture<U> asyncSupplyStage(Executor screenExecutor, Supplier<U> supplier) {
        return new CompletableFuture<U>();
    }

    static Executor screenExecutor(Executor e) {
        // 如果 USE_COMMON_POOL为true(表示使用 默认的线程池)，并且 入参本身就是 ForkJoinPool.commonPool()
//        if (!USE_COMMON_POOL && e == ForkJoinPool.commonPool())
//            return ASYNC_POOL;

        // 如果传入的线程池为null,则：抛出NPE异常
        if (e == null) throw new NullPointerException();

        // 返回由调用方/程序员所传递的线程池实例
        return e;
    }
}
