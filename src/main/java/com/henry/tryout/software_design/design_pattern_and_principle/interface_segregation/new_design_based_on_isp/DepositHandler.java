package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.new_design_based_on_isp;

// 设计具体针对于请求类型的业务处理类
public class DepositHandler implements TransactionHandler<DepositRequest>{

    @Override
    public void handle(final DepositRequest request) {
        double amount = request.getDepositAmount();
    }
}
