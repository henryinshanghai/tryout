package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.original_design_and_client_usage;

// 添加具体的类 - 用来处理具体的某一类型的请求（转账请求）
public class TransferHandler implements TransactionHandler{
    @Override
    public void handle(TransactionRequest request) {
        double transferAmount = request.getTransferAmount();

    }
}
