package com.henry.tryout.springmvc_dig.servlet_async_03.return_void_01;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.concurrent.TimeUnit;

// 验证：当异步处理的接口没有返回值时，可以把 业务处理封装成 独立的方法，交给工作线程执行
@Slf4j
public class returnVoidDemo01 {

    @GetMapping("testAsync1")
    public void testAsync1() {
        log.info("开始处理异步请求");
        // 委托给 专门的线程 来 执行任务
        new Thread(this::asyncExecute1).start();
        log.info("异步请求处理结束");
    }

    // 把 实际的业务处理 封装成方法👇
    @SneakyThrows
    public void asyncExecute1() {
        log.info("业务处理开始");
        TimeUnit.SECONDS.sleep(10);
        log.info("业务处理结束");
    }
}
