package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.factory_05;

public class client_using_factory_02 {
    public static void main(String[] args) {
        // client可以直接使用 工厂类的create()方法 来 创建实例对象
        Product loan = ProductFactory_01.createProduct("loan");
    }
}
