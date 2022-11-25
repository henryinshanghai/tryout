package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.original_design_and_client_usage;

// #2 设计一个接口 用来一般地处理业务请求
public interface TransactionHandler {
    // 抽象方法：处理请求
    void handle(TransactionRequest request);
}

