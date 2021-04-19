package com.henry.tryout.async_program.via_future_in_JDK_02.workingMechanism_05;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class AsyncRunStage_comments_01 {

    /*
        核心方法之 asyncRunStage()
     */
    static CompletableFuture<Void> asyncRunStage(Executor e, Runnable f) {
        // 1 如果任务或者行为为null,则 抛出NPE异常
        if (f == null) throw new NullPointerException();
        // 2 创建一个future对象
        CompletableFuture<Void> d = new CompletableFuture<Void>();

        // 3 包装f(要执行的行为) 和d(创建的future对象)为异步任务后，把它们投递到线程池中
//        e.execute(new CompletableFuture.AsyncRun(d, f));

        // 4 返回创建的future对象
        return d;
    }

}
