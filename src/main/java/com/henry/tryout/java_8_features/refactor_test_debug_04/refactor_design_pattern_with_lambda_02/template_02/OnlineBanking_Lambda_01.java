package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.template_02;

import java.util.function.Consumer;

public class OnlineBanking_Lambda_01 {
    // 使用Lambda表达式，可以把 待改变的行为 作为参数传递给 模板方法本身 - 省去了 子类这种僵化的代码
    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer customer = new Customer(id);

        // accept()方法的函数描述符  void accept(T t);
        makeCustomerHappy.accept(customer);

    }
}
