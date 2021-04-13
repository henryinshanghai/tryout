package com.henry.tryout.async_program.via_future_in_JDK_02.futuretask_01;

import java.util.concurrent.*;

public class Async_threadpool_with_futureTask_on_submit_03 {

    // 有返回值的任务A
    public static String doSomethingA() {

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA---");

        return "TaskAResult";
    }

    // 有返回值的任务B
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
                    1,
                    TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(5),
                    new ThreadPoolExecutor.CallerRunsPolicy()
            );

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        long start = System.currentTimeMillis();

        // 1.开启异步单元执行任务A
        // 把 定义异步任务 与 执行异步任务 的代码放在一起写 - 手段：线程池对象的submit()方法
        // note： lambda表达式的写法
        Future<String> futureTask = POOL_EXECUTOR.submit(() -> {
            String result = null;
            try {
                result = doSomethingA();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        });

        // 2.执行任务B - 手段：由main线程执行任务B
        String taskBResult = doSomethingB();

        // 3.同步等待 - main线程阻塞，以等待线程A运行结束 由于main线程阻塞，所以 below的打印语句 一定后于 任务中的打印语句 执行
        String taskAResult = futureTask.get();

        // 4.打印两个任务执行结果
        System.out.println(taskAResult + " " + taskBResult);
        System.out.println(System.currentTimeMillis() - start);
    }

}
