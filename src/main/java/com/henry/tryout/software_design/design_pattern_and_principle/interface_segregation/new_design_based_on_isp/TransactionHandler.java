package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.new_design_based_on_isp;

// #4 设计一个接口 TransactionHandler - 添加处理具体业务的方法；
// 特征：泛型参数为 T extends TransactionRequest, 抽象方法的参数类型为 T
public interface TransactionHandler<T extends TransactionRequest> {
    void handle(T request);
}
