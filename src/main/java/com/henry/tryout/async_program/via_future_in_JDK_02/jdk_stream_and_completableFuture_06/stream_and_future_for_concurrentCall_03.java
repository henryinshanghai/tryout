package com.henry.tryout.async_program.via_future_in_JDK_02.jdk_stream_and_completableFuture_06;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class stream_and_future_for_concurrentCall_03 {
    public static String rpcCall(String ip, String param) {

        System.out.println(ip + " rpcCall:" + param);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return param;

    }

    public static void main(String[] args) {


        // 1.生成ip列表
        List<String> ipList = new ArrayList<String>();
        for (int i = 1; i <= 10; ++i) {
            ipList.add("192.168.0." + i);
        }

        // 2.并发调用
        long start = System.currentTimeMillis();
        // note：由于map操作中使用了 CompletableFuture的操作，所以这里返回的list其实是一个 CompletableFuture对象的list
        List<CompletableFuture<String>> futureList = ipList.stream()
                .map(ip -> CompletableFuture.supplyAsync(
                        () -> rpcCall(ip, ip)
                        )
                ) // 把映射这一步的同步操作 转化为 异步操作 - 手段：使用CompletableFuture.supplyAsync()
                .collect(Collectors.toList()); // 收集处理结果

        // 等待所有异步任务都执行完毕
        // note：由于map操作
        List<String> resultList = futureList.stream()
                // 把future对象 转化成为 future对象的执行结果
                .map(future -> future.join()) // 同步等待结果
                .collect(Collectors.toList());

        // 3.输出
        resultList.stream().forEach(r -> System.out.println(r));

        System.out.println("cost:" + (System.currentTimeMillis() - start));


    }
}
/*
总耗时：2048ms

对比于 stream_usage_02.java 运行的总耗时可知：
    这里的rpcCall()的调用是 并行进行的。

note：虽然这10个 rpcCall()请求是 并行发生的，但并不能确定哪一个调用会率先结束。所以：
    仍旧需要使用 future.join()的方式，来阻塞等待任务执行完成；

至于，10个rpcCall()调用是不是全部都是并行进行的。这取决于：
    1 CompletableFuture中线程池的个数；
    2 运行程序的电脑的核心数量。
 */
