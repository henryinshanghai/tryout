package com.henry.tryout.java_8_features.optional_06.optional_usage_pattern_03;

import com.henry.tryout.java_8_features.optional_06.optional_in_java_02.Insurance;

import java.util.Optional;

// 验证：当使用if条件做属性值的筛选时，需要先判断不为null，然后才能取值 - 否则就会有NPE的风险
// 验证： 使用optional对象的 filter()方法 + ifPresent()方法 - 这种方式，不会出现NPE。因为参数为null时，调用不会发生
public class Optional_Usage_Pattern_03 {
    public static void main(String[] args) {
        // 使用filter筛选掉某些特定的值
        filterInOldFashion();

        filterWithOptionalApproach();
    }

    private static void filterWithOptionalApproach() {
        Optional<Insurance> optInsurance = Optional.of(new Insurance());

        // 使用optional对象的filter()来做筛选
        /*
            特征：
                1 filter()方法中使用的对象 就是一个 已经解引用的对象 - 不用担心会导致NPE（因为如果对象为null，调用根本就不会发生）
                2 如果filter的结果为true，则：filter()方法会直接返回 Optional对象。否则，把当前值过滤掉，把optional对象的值置空
         */
        optInsurance.filter(insurance ->
                        "CambridgeInsurance".equals(insurance.getName()))
                .ifPresent(x -> System.out.println("Okay")); // 区分 isPresent() 与 ifPresent()
    }

    private static void filterInOldFashion() {
        Insurance insurance = new Insurance();

        // 使用if条件句来做筛选
        if (insurance != null && "CambridgeInsurance".equals(insurance.getName())) {
            System.out.println("Okay");
        }
    }
}
