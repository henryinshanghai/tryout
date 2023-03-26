package com.henry.tryout.springmvc_dig.servlet_async_03.return_deferredResult_04;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 验证：开发者可以使用 DeferredResult 来 自行制定 异步任务的开始与结束
// 手段：#1 使用 createTask来创建任务 并且 在初始化时设置一个超时时间；
// #2 把任务保存到 任务队列（map对象）中；
// #3 在合适的时机，开发者可以使用另一个请求来 设置任务的结束状态
// 作用：异步处理只会在两种情况下结束 - {#1 得到开发者所发出的完成通知； #2 请求时间超时}
@Slf4j
public class manuallyCallSetResultDemo {
    private static final Map<String, DeferredResult<String>> taskMap = new ConcurrentHashMap<>();
    
    //创建任务
    @RequestMapping("/createTask")
    public DeferredResult<String> createTask(String uuid) {
        log.info("ID[{}]任务开始",uuid);
        StopWatch stopWatch = new StopWatch(" DeferredResult test 容器线程");
        stopWatch.start("容器线程");
        log.info("返回值DeferredResult 异步请求开始");
        //超时时间100s
        DeferredResult<String> deferredResult = new DeferredResult<>(100000L);
        StopWatch t = new StopWatch(" DeferredResult test 工作线程");
        t.start("工作线程");
        deferredResult.onCompletion(()->{
            log.info("返回值DeferredResult onCompletion 工作线程处理完毕");
            t.stop();
            log.info(String.format("%s秒",t.getTotalTimeSeconds()));
        });
        taskMap.put(uuid, deferredResult);
        log.info("返回值DeferredResult 异步请求结束");
        stopWatch.stop();
        log.info(String.format("%s秒",stopWatch.getTotalTimeSeconds()));
        return deferredResult;
    }

    //查询任务状态
    @RequestMapping("/queryTaskState")
    public String queryTaskState(String uuid) {
        DeferredResult<String> deferredResult = taskMap.get(uuid);
        if (deferredResult == null) {
            return "未查询到任务,uid:" + uuid;
        }
        if (deferredResult.hasResult()) {
            return deferredResult.getResult().toString();
        } else {
            log.info("ID[{}]任务进行中",uuid);
            return "进行中";
        }
    }

    //模拟第三方调用通知任务结束
    @RequestMapping("/changeTaskState")
    public String changeTaskState(String uuid) {
        DeferredResult<String> deferredResult = taskMap.remove(uuid);
        if (deferredResult == null) {
            return "未查到到任务";
        }
        if (deferredResult.hasResult()) {
            return "已完成，无需再次设置";
        } else {
            //未完成设置为完成
            deferredResult.setResult("已完成");
            log.info("将任务ID{},设置为处理完成",uuid);
            return "已完成";
        }
    }
}
