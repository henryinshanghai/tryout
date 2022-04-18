package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.template_02;

public class Client_02 {
    public static void main(String[] args) {
        new OnlineBanking_Lambda_01().processCustomer(1337,
                (Customer customer) -> System.out.println("Hello " + customer.getName()));
    }
}
