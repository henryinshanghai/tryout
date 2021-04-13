package com.henry.tryout.async_program.via_future_in_JDK_02.compeletableFuture_02;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class asyncTask_in_sequence_via_thenAccept_pickResultUp_06 {

    public static void thenAccept() throws ExecutionException, InterruptedException {
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

        // 2 在future_one 上 添加新的任务
        // 当 future_one结束时，新的任务就会被执行，并会返回新的future
        CompletableFuture<Void> future_two = future_one.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                // 对 future_one返回的结果 进行处理
                // note： 这里的新任务 能够 获取到先前任务的处理结果
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println("--- after task_one finished, do this: ---" + (s + " & superman"));

            }
        });

        // 3 在方法中调用get()方法 - 阻塞以等待任务序列逐一结束
        System.out.println(future_two.get());

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        thenAccept();
    }
}
/*
启示：
    1 thenAccept()中的accept()回调方法，能够以方法参数的形式 获取到 future_one任务的执行结果；
    2 由于future_two中的任务没有返回值，所以预期输出为 null
 */
