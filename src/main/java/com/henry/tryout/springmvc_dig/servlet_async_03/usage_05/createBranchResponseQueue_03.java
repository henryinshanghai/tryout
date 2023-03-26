package com.henry.tryout.springmvc_dig.servlet_async_03.usage_05;

// #3 “创建分支服务” 收到消息后根据“分支名” 拿到“对应的deferredResult” 来 更新分支信息，设置响应结果，结束请求。
// 至此创建分支请求完成，用户无延迟的收到了响应。
public class createBranchResponseQueue_03 {

    @ZZMQListener(group = "${create-branch-response-queue}")
    public void onMessage(MessageExt messageExt) {
        log.info("=============== 接受创建分支结果信息 ->{}", messageExt.toString());
        Response response = JsonUtil.string2Object(messageExt.getBody(), Response.class);
        DeferredResult<String> deferredResult = taskMap.get(response.getBranchName());
        //更新分支信息
        branchManageService.updateBranch(response.getStatus());
        //结束异步
        deferredResult.setResult(response.getResult());
    }
}
