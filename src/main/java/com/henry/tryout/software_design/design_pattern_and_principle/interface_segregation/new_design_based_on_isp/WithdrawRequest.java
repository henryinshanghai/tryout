package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.new_design_based_on_isp;

// 瘦接口 * 2
public interface WithdrawRequest extends TransactionRequest{
    double getWithdrawAmount();
}
