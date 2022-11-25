package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.new_design_based_on_isp;

// #3 添加一个类 ActualTransactionRequest 来 作为真实的业务请求类
// 特征：实现了 所有的瘦接口，进而包含了所有需要的方法
public class ActualTransactionRequest implements
        DepositRequest, WithdrawRequest, TransferRequest,
        LivingPaymentRequest{

    @Override
    public double getDepositAmount() {
        return 0;
    }

    @Override
    public double getTransferAmount() {
        return 0;
    }

    @Override
    public double getWithdrawAmount() {
        return 0;
    }

    @Override
    public double getLivingPaymentAmount() {
        return 0;
    }
}
