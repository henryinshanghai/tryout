package com.henry.tryout.async_program.via_future_in_JDK_02.compeletableFuture_02;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class asyncTask_in_sequence_via_thenRun_05 {

    public static void thenRun() throws ExecutionException, InterruptedException {
        // 定义任务（用于异步执行），并返回future对象
        CompletableFuture<String> future_one = CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {
                // 任务本身
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 返回任务的执行结果
                return "hello henry";
            }
        });

        // 2 在future_one上 添加 事件 - 当future_one执行完成后，回调此事件。并返回新的future
        CompletableFuture<Void> future_two = future_one.thenRun(new Runnable() {
            @Override
            public void run() {
                // 当 future_one 结束后，开始做另一个任务
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getName());
                System.out.println("--- after future_one over, so this ---");
            }
        });

        // 3 在方法中调用get()方法 - 阻塞以等待任务序列逐一结束
        System.out.println(future_two.get()); // 预期返回：null
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenRun();
    }
}
/*
启示：
    对于task序列来说：future_one执行结束后，线程会再去执行 thenRun()中定义的新任务；
 */
