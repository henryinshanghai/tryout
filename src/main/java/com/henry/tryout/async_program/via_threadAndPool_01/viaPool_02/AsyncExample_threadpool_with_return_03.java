package com.henry.tryout.async_program.via_threadAndPool_01.viaPool_02;

import java.util.concurrent.*;

public class AsyncExample_threadpool_with_return_03 {


    public static String doSomethingA() { // String类型的返回值

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--- doSomethingA---");
        return "A Task Done";
    }

    // 0自定义线程池
    private final static int AVALIABLE_PROCESSORS = Runtime.getRuntime().availableProcessors();

    private final static ThreadPoolExecutor POOL_EXECUTOR =
            new ThreadPoolExecutor(AVALIABLE_PROCESSORS,
                    AVALIABLE_PROCESSORS * 2,
                    1, // 非核心的 不活跃线程 的最大存活时间（临时工）
                    TimeUnit.MINUTES,
                    new LinkedBlockingQueue<>(5),
                    new NamedThreadFactory("ASYNC-POOL"),
                    new ThreadPoolExecutor.CallerRunsPolicy());

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1.开启异步单元执行任务A
        // 向线程池中 添加一个 有返回值的任务  - 手段：lambda表达式 来 表示Callable类型的异步任务
        // 特征：执行submit()方法后，方法会马上返回一个future对象
        Future<?> resultA = POOL_EXECUTOR.submit(() -> doSomethingA());

        // 2.同步等待执行结果的返回值 - 手段：调用future对象的get()方法
        // 特征：get()方法会阻塞 main线程
        System.out.println(resultA.get());
    }
}
/*
启示：

线程池的工作流程：（像是一家小公司）
开发者提交任务：
1 判断 线程池中的核心线程 是不是已经满员：
    否：创建新线程来 执行任务
    是：判断 任务队列 是不是已经满员了：
        否： 把任务放到 任务队列中，等待线程来执行它
        是： 判断 线程池中的线程 是不是已经满员：
            否： 创建 新线程 来 执行任务；
            是： 按照 特定的拒绝策略 来 处理 无法执行的任务；

线程池：一家快递公司实体；
核心线程：公司里的正式员工；
其他线程：公司里面的临时工；
任务队列：公司办公室存放快递的房间；
拒绝策略：当公司已经无法处理更多的快件时，如何对外宣布。

有返回值的任务：
    1 使用submit()方法，传入任务；
    2 使用Future对象，接收快速响应；
    3 使用get()方法， 阻塞式地获取 任务执行的返回值
 */
