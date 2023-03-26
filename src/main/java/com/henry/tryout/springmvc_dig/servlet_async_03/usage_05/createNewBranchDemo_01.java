package com.henry.tryout.springmvc_dig.servlet_async_03.usage_05;

import org.dom4j.Branch;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// #1 使用map对象 对 deferredResult进行存储
public class createNewBranchDemo_01 {
    private static final Map<String, DeferredResult<String>> taskMap = new ConcurrentHashMap<>();

    @RequestMapping("/createTask")
    public DeferredResult<String> createBranch(String projectName) {
        Branch branch = branchManageService.createBranch(projectName);
        DeferredResult<String> deferredResult = new DeferredResult<>(5000L);
        deferredResult.onCompletion(() -> taskMap.remove(branch.getBranchName()));
        deferredResult.onTimeout(() -> taskMap.remove(branch.getBranchName()));

        // 发送创建分支请求到队列
        this.createBranchMQSender.send(branch);
        // 把对应的deferredResult保存下来，等待后续调用
        taskMap.putIfAbsent(branchName, deferredResult);
        // 最后返回deferredResult。此时容器线程已经释放
        return deferredResult;
    }
}
