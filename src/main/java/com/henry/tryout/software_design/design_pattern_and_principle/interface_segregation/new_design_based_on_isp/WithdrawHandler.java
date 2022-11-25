package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.new_design_based_on_isp;

public class WithdrawHandler implements TransactionHandler<WithdrawRequest>{
    @Override
    public void handle(final WithdrawRequest request) {
        double amount = request.getWithdrawAmount();
    }
}
