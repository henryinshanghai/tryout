package com.henry.tryout.software_design.design_pattern_and_principle.interface_segregation.new_design_based_on_isp;

public interface LivingPaymentRequest extends TransactionRequest{
    double getLivingPaymentAmount();
}
