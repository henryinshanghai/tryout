package com.henry.tryout.java_8_features.optional_06.optional_in_action_04;

import java.util.Optional;

public class OptionalUtility {

    // 把原始API抛出异常的场景，转化成为一个 optional的空对象 - 好处：client代码不再需要 笨拙的 try/catch语法了
    // 好的做法： 把方法放到一个 OptionalUtility工具类中
    public static Optional<Integer> stringToInt(String s) {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException e) {
            return Optional.empty(); // 捕获异常之后，返回一个 空的optional对象
        }
    }
}
