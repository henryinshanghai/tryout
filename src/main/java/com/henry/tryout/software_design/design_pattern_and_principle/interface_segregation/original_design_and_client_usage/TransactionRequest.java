package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.original_design_and_client_usage;

// #1 设计一个类 来 表示业务请求/用户请求
public class TransactionRequest {
    // 获取操作类型
    TransactionType getType() {
        return null;
    }

    // 获取存款金额
    double getDepositAmount() {
        return 0.0;
    }

    // 获取取款金额
    double getWithdrawAmount() {
        return 0.0;
    }

    // 获取转账金额
    double getTransferAmount() {
        return 0.0;
    }
}

class TransactionType {
    
}