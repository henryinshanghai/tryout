package com.henry.tryout.java_8_features.optional_06.optional_in_action_04;

import java.util.Optional;

public class using_optional_to_capsule_throw_exception_02 {
    public static void main(String[] args) {
        // 除了返回一个null,另外一个常见的 表示值不存在的方式是 - 抛出一个异常
        // 这时候client代码需要使用 try/catch 语法

        Optional<Integer> intValue = OptionalUtility.stringToInt("12345");
        System.out.println("12345的数值为： " + intValue);

        Optional<Integer> henryString = OptionalUtility.stringToInt("henryIsCool");
        System.out.println("henryString 的数值为： " + henryString);

    }



}
