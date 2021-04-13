package com.henry.tryout.async_program.via_future_in_JDK_02.compeletableFuture_02;

import java.util.concurrent.*;

public class non_return_AsyncTask_via_runAsync_02 {
    // 创建线程池对象
    private final static ThreadPoolExecutor myPoolExecutor =
            new ThreadPoolExecutor(
                    8,
                    8,
                    1,
                    TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(10));


    public static void runAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                // 定义异步任务
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 设置异步任务的执行结果
                System.out.println("the async task is over here!"); // first
            }
        });

        // 在方法体中等待异步任务执行完成 - 并获取执行的结果
        System.out.println(future.get()); // 预期：null second
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        runAsync();
    }
}
