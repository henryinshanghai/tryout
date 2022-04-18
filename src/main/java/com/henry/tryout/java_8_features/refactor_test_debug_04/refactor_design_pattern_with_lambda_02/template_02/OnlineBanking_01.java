package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.template_02;

import java.util.function.Consumer;

public abstract class OnlineBanking_01 {

    // 旧的方式实现 模板设计模式，需要准备子类，并在子类中实现抽象方法 makeCustomerHappy()
    public void processCustomer(int id) {
        Customer customer = new Customer(id); // Database.getCustomerWIthId(id);

        makeCustomerHappy(customer);
    }

    abstract void makeCustomerHappy(Customer customer);

    // 使用Lambda表达式，可以把 待改变的行为 作为参数传递给 模板方法本身 - 省去了 子类这种僵化的代码
    // 参考：OnlineBanking_Lambda_01
}
