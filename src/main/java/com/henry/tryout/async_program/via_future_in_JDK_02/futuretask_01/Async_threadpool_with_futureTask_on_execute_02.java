package com.henry.tryout.async_program.via_future_in_JDK_02.futuretask_01;

import java.util.concurrent.*;

public class Async_threadpool_with_futureTask_on_execute_02 {
    // 有返回值的异步任务A
    public static String doSomethingA() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA---");

        return "TaskAResult";
    }

    // 有返回值的异步任务B
    public static String doSomethingB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingB---");
        return "TaskBResult";

    }

    // 0自定义线程池
    private final static int AVAILABLE_PROCESSORS =
            Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(
                    AVAILABLE_PROCESSORS,
                    AVAILABLE_PROCESSORS * 2,
                    1, // 非核心的 空闲线程的存活时间
                    TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(5),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        // 1.创建future任务 - 手段：FutureTask的构造器方法 + lambda表达式
        FutureTask<String> futureTask = new FutureTask<String>(() -> {
            String result = null;
            try {
                result = doSomethingA();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });

        // 2.开启异步单元执行任务A - 把异步任务对象 丢到 线程池中执行
        POOL_EXECUTOR.execute(futureTask);

        // 3.执行任务B - 在main线程中 执行任务B
        String taskBResult = doSomethingB();

        // 4.同步等待 - main线程阻塞，以等待线程A运行结束 由于main线程阻塞了， 所以下面的sout语句 一定后于 任务中的语句 打印
        String taskAResult = futureTask.get();

        // 5.打印两个任务执行结果
        System.out.println(taskAResult + " " + taskBResult);
        // 打印耗时
        System.out.println(System.currentTimeMillis() - start);
    }
}
