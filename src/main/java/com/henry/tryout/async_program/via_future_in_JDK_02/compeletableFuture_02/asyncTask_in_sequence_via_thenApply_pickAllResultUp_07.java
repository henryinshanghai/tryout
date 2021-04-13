package com.henry.tryout.async_program.via_future_in_JDK_02.compeletableFuture_02;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;

public class asyncTask_in_sequence_via_thenApply_pickAllResultUp_07 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<String> future_one = CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {
                // 任务本身
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 任务的计算结果
                return "hello";
            }
        });

        // 2 在future_one 上添加新的任务，并返回新的future
        CompletableFuture<String> future_two = future_one.thenApply(new Function<String, String>() {

            // 在 future_one返回结果的基础上，继续运算
            @Override
            public String apply(String s) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return s + " & batman";
            }
        });

        // 3 在方法中调用get()方法 - 阻塞以等待任务序列逐一结束
        // note: 由于apply()方法有返回值，所以这里的get()是能够获取到结果的
        System.out.println(future_two.get());
    }
}
/*
启示：
    对于任务序列中的每一个任务都有返回值的情况，使用future对象的thenApply()方法
 */
