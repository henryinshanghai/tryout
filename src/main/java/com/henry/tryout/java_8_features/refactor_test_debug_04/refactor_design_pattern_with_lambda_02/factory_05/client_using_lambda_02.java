package com.henry.tryout.java_8_features.refactor_test_debug_04.refactor_design_pattern_with_lambda_02.factory_05;

import com.google.common.base.Supplier;

import java.util.HashMap;
import java.util.Map;

public class client_using_lambda_02 {
    // 建立 productName -> product构造函数的映射关系
    final static Map<String, Supplier<Product>> map = new HashMap<>();
    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }

    // 使用 上面的map对象 来 实例化不同的产品 - 效果与使用工厂类的create()方法完全相同
    public static Product createProduct(String name) {
        Supplier<Product> productSupplier = map.get(name);
        if(productSupplier != null) return productSupplier.get();
        throw new IllegalArgumentException("No such product: " + name);
    }

    public static void main(String[] args) {
        Product loan = createProduct("loan");
    }
}
