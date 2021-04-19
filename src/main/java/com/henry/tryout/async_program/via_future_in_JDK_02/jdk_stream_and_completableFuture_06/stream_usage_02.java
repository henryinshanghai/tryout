package com.henry.tryout.async_program.via_future_in_JDK_02.jdk_stream_and_completableFuture_06;

import java.util.ArrayList;
import java.util.List;

public class stream_usage_02 {

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

        // 2.发起广播调用 - 向ip列表中的每一个ip发起调用
        long start = System.currentTimeMillis();
        List<String> result = new ArrayList<>();
        for (String ip : ipList) {
            result.add(rpcCall(ip, ip));
        }

        // 3.输出 - 使用stream来处理 result集合
        result.stream().forEach(r -> System.out.println(r));
        System.out.println("cost:" + (System.currentTimeMillis() - start));

    }


}
/*
总耗时：10065ms；

 */
