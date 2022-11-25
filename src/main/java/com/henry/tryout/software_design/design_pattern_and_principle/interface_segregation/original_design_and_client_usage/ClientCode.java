package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.original_design_and_client_usage;

import java.util.HashMap;
import java.util.Map;

// client: 基础设计的使用方
public class ClientCode {

    public void clientUsingDesignResult(TransactionRequest request) { // #1 使用设计的业务请求类作为参数 - request需要包含请求类型
        // #2 创建 请求类型 -> 请求处理器的映射对象
        Map<TransactionType, TransactionHandler> typeToHandlerMap = new HashMap<>();

        /* 术语：业务分发 👇 */
        // #3 根据 传入的请求类型，获取到其对应的处理器
        TransactionHandler handler = typeToHandlerMap.get(request.getType());

        // #4 调用处理器的handle()方法 来 真正地处理业务请求
        if (handler != null) {
            handler.handle(request);
        }
    }
}

