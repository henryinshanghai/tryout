package com.henry.tryout.async_program.via_future_in_JDK_02.completableFuture_multiple_calculate_03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class asyncTask_with_coupleOf_completableFuture_via_allOf_02 {

    // 1.异步任务，返回future
    public static CompletableFuture<String> doSomethingOne(String id) {
        // 1.1创建异步任务
        return CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {

                // 1.1.1休眠1s，模拟任务计算
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("compute " + id);

                return id;
            }
        });
    }

    // 2.开启异步任务，返回future
    public static CompletableFuture<String> doSomethingTwo(String id) {
        return CompletableFuture.supplyAsync(new Supplier<String>() {

            @Override
            public String get() {

                // 2.1,休眠3s，模拟计算
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("compute " + id);

                return id;
            }
        });
    }

    public static void allOf() throws InterruptedException, ExecutionException {
        // 1.创建future列表
        List<CompletableFuture<String>> futureList = new ArrayList<>();
        futureList.add(doSomethingOne("1"));
        futureList.add(doSomethingOne("2"));
        futureList.add(doSomethingOne("3"));
        futureList.add(doSomethingOne("4")); // 调用 doSomethingOne()四次

        // 2.转换多个future为一个
        CompletableFuture<Void> result = CompletableFuture
                .allOf(futureList.toArray(new CompletableFuture[futureList.size()]));

        // 3.等待所有future都完成
        System.out.println(result.get()); // 这里会打印出来什么？ 为什么会是null   这个result变量之后就没有再使用了

        // 4.等所有future执行完毕后，获取所有future的计算结果 - 手段：在 futureList上进行流式处理
        CompletableFuture<List<String>> finallyResult =
                result.thenApply(new Function<Void, List<String>>() {

                // 返回 已经执行结束的future对象的list
                @Override
                public List<String> apply(Void t) {
                    return futureList.stream()
                            .map(future -> future.join()) // 筛选出来那些个 join() 执行完成的任务
                            .collect(Collectors.toList()); // 把结果放到一个容器中去
                }
            });

        // 5.打印所有future的结果
        for (String str : finallyResult.get()) { // 这里get()得到的值是： 各个任务的返回值的集合
            System.out.println(str);
        } // 1 2 3 4
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // 1.allOf
        allOf();

        // 2.anyOf
        // anyOf();

        Thread.sleep(10000);
    }


}
