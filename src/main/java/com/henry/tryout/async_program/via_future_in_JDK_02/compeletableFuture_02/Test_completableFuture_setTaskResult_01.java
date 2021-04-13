package com.henry.tryout.async_program.via_future_in_JDK_02.compeletableFuture_02;

import java.util.concurrent.*;

public class Test_completableFuture_setTaskResult_01 {

    // 创建线程池对象
    private final static int AVAILABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(
                    AVAILABLE_PROCESSORS,
                    AVAILABLE_PROCESSORS * 2,
                    1,
                    TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(5),
                    new ThreadPoolExecutor.CallerRunsPolicy()
            );

    public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
        // 1 创建一个 CompletableFuture对象
        CompletableFuture<String> future = new CompletableFuture<String>();

        // 2 获取线程来执行任务
        POOL_EXECUTOR.execute(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            /* note：设置计算结果到 future对象中 */
            System.out.println("---" + Thread.currentThread().getName() + "set future result here ---");
            future.complete("hello henry!");
        });

        // 3 在 main线程中，调用get()方法 - 阻塞等待任务执行完成&任务执行的结果
        System.out.println("+++ main thread waiting for task execute completely. +++");
        System.out.println(future.get());
        System.out.println("+++ main thread got the future task result");

    }
}
/*
启示：
    1 向future对象中 添加异步任务执行的结果 - future.complete();
    2 在任务执行结束之前，对get()方法的调用会 阻塞main线程。

 */
