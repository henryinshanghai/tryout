package com.henry.tryout.springmvc_dig.servlet_async_03.usage_05;

// #2 在“创建资源服务” 收到创建分支mq消息后，开始创建相关资源，然后将结果放入 “创建分支响应队列”。交给“创建分支服务”消费
public class createBranchRequestQueue_02 {

    @ZZMQListener(group = "${create-branch-request-queue}")
    public void onMessage(MessageExt messageExt) {
        log.info("=============== 接受创建分支消息 ->{}", messageExt.toString());
        // 创建gitlab分支
        // 创建jenkins build job
        // 创建jenkins sonar job
        // 关联tapd需求
        TimeUnit.SECONDS.sleep(10);
        // 响应结果
        Response response = new Response();
        //发送响应结果
        this.responseCreateBranchSender.send(response);
    }
}
