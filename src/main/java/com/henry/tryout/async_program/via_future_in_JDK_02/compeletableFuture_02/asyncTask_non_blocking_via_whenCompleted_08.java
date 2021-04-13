package com.henry.tryout.async_program.via_future_in_JDK_02.compeletableFuture_02;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class asyncTask_non_blocking_via_whenCompleted_08 {

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {

        // 1.创建一个CompletableFuture对象
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {
                // 1.1模拟异步任务执行
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // 1.2返回计算结果
                return "hello, henry";
            }
        });

        // 2.添加回调函数
        future.whenComplete(new BiConsumer<String, Throwable>() {

            @Override
            public void accept(String t, Throwable u) {
                // 2.1如果没有异常，打印异步任务结果
                if (null == u) {
                    System.out.println("1");
                    System.out.println(t);
                } else {
                    // 2.2打印异常信息
                    System.out.println(u.getLocalizedMessage());
                }
            }
        });

        // 3.挂起当前线程，等待异步任务执行完毕
        Thread.currentThread().join(); // 😳 为了能够保证 回调函数正常执行，这里还是需要挂起当前线程 这不还是阻塞了嘛？！
    }

}
/*
当需要 异步任务执行完成后，再做一些事情。而不希望main线程被阻塞时，
使用 future对象的 whenCompleted()方法
 */
