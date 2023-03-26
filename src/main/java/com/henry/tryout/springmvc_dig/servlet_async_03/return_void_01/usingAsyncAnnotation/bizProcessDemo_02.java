package com.henry.tryout.springmvc_dig.servlet_async_03.return_void_01.usingAsyncAnnotation;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

@Slf4j
public class bizProcessDemo_02 {
    @Async
    @SneakyThrows
    public void asyncExecute2(){
        StopWatch stopWatch = new StopWatch(" @Async test 工作线程");
        stopWatch.start("工作线程");
        log.info("业务处理开始");
        TimeUnit.SECONDS.sleep(10);
        log.info("业务处理结束");
        stopWatch.stop();
        log.info(String.format("%s秒",stopWatch.getTotalTimeSeconds()));
    }
}
