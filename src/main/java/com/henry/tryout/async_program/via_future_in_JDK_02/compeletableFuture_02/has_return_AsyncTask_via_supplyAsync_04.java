package com.henry.tryout.async_program.via_future_in_JDK_02.compeletableFuture_02;

import java.util.concurrent.*;
import java.util.function.Supplier;

public class has_return_AsyncTask_via_supplyAsync_04 {
    // 创建线程池对象
    private final static ThreadPoolExecutor myPoolExecutor =
            new ThreadPoolExecutor(
                    8,
                    8,
                    1,
                    TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(10));

    public static void supplyAsync() throws ExecutionException, InterruptedException {
        // 定义任务（用于异步执行），并返回future
        CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {
            // 任务本身
            @Override
            public String get() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 设置任务的计算/执行结果
                return "hello Annie";
            }
        });

        // 在方法体中，调用get()方法 - 阻塞以等待异步任务执行完成
        System.out.println("waiting~~~");
        System.out.println(future.get()); // 打印任务执行的结果/返回值： hello annie
        System.out.println("done~~~");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        supplyAsync();
    }
}
