package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.new_design_based_on_isp;

public class LivingPaymentHandler implements TransactionHandler<LivingPaymentRequest>{

    @Override
    public void handle(LivingPaymentRequest request) {
        double amount = request.getLivingPaymentAmount();
    }
}
