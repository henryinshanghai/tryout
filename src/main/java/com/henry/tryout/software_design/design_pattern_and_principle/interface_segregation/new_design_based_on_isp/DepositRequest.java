package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.new_design_based_on_isp;

// 瘦接口1：DepositRequest
// 业务接口 继承自 通用接口
public interface DepositRequest extends TransactionRequest{
    double getDepositAmount();
}
