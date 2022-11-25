package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.new_design_based_on_isp;

// 具体地处理业务
public class TransferHandler implements TransactionHandler<TransferRequest>{

    @Override
    public void handle(TransferRequest request) {
        double amount = request.getTransferAmount();
    }
}
