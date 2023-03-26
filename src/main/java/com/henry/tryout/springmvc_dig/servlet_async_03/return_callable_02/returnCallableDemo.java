package com.henry.tryout.springmvc_dig.servlet_async_03.return_callable_02;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

// 验证：当 ”异步处理有返回值“的时候，虽然主线程会先行释放，但是响应并未结束 - aka 接口要在 业务代码/异步处理完成后，才会成功响应
// 手段：@ResponseBody注解
// 原理：Spring封装了 Servlet3.0 对异步处理的支持
@Slf4j
public class returnCallableDemo {

    @GetMapping("/testCallable")
    @ResponseBody
    public Callable<String> test2() {
        StopWatch stopWatch = new StopWatch(" Callable test 容器线程");
        stopWatch.start("容器线程");
        log.info("返回值Callable 异步请求开始");

        Callable<String> callable = () -> {
            StopWatch stopWatch1 = new StopWatch(" Callable test 工作线程");
            stopWatch1.start("工作线程");
            log.info("返回值Callable 业务处理开始");
            //模拟结果延时返回
            Thread.sleep(10000);
            log.info("返回值Callable 业务处理结束");
            stopWatch1.stop();
            log.info(String.format("%s秒",stopWatch1.getTotalTimeSeconds()));
            return "OK";
        };

        log.info("返回值Callable 异步请求结束");
        stopWatch.stop();

        log.info(String.format("%s秒",stopWatch.getTotalTimeSeconds()));
        return callable;
    }
}
