package com.henry.tryout.async_program.via_future_in_JDK_02.compeletableFuture_02;

import java.util.concurrent.*;

public class non_return_AsyncTask_via_runAsync_using_ownThreadPool_03 {

    // 创建线程池对象
    private final static ThreadPoolExecutor myPoolExecutor =
            new ThreadPoolExecutor(
                    8,
                    8,
                    1,
                    TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(10));


    /* 对于没有返回值的异步任务 - 使用其特定的线程(而不是默认线程池)来执行任务 */
    public static void runAsyncWithBizExecutor() throws ExecutionException, InterruptedException {
        // 1 定义异步任务，并返回future
        CompletableFuture future = CompletableFuture.runAsync(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("task is over here~");
            }
        }, myPoolExecutor); // note：这里提供的 线程池 会替换掉默认的线程池 commonPool()

        // 在执行此方法的线程中 调用get()方法，阻塞以等待任务执行完成
        System.out.println(future.get()); // 预期为 null
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        runAsyncWithBizExecutor();
    }
}
