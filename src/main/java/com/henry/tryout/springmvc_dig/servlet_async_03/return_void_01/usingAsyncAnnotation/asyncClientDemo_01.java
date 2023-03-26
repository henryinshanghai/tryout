package com.henry.tryout.springmvc_dig.servlet_async_03.return_void_01.usingAsyncAnnotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;

// 验证：在 Spring中实现 ”不关心接口返回值的异步处理“，可以使用 @Async注解
// 特征：业务处理代码 与 发起异步请求的代码 不能在同一个类中
// 原理：Spring会为 @Async注解来创建一个JDK动态代理，从而发挥作用。
// 但如果 业务处理代码 与 异步请求的调用代码在同一个类中，则：代理类无法发挥作用
// 验证：通过日志可知，主线程结束后，工作线程才开始执行 - aka, 任务被异步处理了
@Slf4j
public class asyncClientDemo_01 {
    private bizProcessDemo_02 bizProcessDemo;

    @GetMapping("testAsync2")
    public void testAsync2(){
        StopWatch stopWatch = new StopWatch(" @Async test 容器线程");
        stopWatch.start("容器线程");
        log.info("异步请求开始");
        bizProcessDemo.asyncExecute2();
        log.info("异步请求结束");
        stopWatch.stop();
        log.info(String.format("%s秒",stopWatch.getTotalTimeSeconds()));
    }
}
