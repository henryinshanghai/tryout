package com.henry.tryout.java_8_features.optional_06.optional_in_action_04;

import java.util.Optional;

// 验证：ofNullable() 用于接收一个可能为null的参数，返回一个 optional对象；
// 验证：flatMap() 用于把参数（不管是原始对象 还是 optional对象），都扁平化成为一个 optional对象；
// 验证：filter() 用于过滤optional对象所封装的value；
// 验证： orElse() 用于在取值为null时，提供一个默认的值；
public class using_optional_to_avoid_trivial_grammar_03 {
    public static void main(String[] args) {
        /*
            业务：
                1 一堆的属性 -> 值的映射；
                2 client代码需要读取这些属性，如果属性的值大于0，返回属性值。如果是其他情况，则返回0
         */
        Properties properties = new Properties();
        properties.init();

        int result = readDurationNotUsingOptional(properties, "atrrA");
        int result_optional = readDurationUsingOptional(properties, "atrrA");
    }

    private static int readDurationUsingOptional(Properties properties, String name) {
        return Optional.ofNullable(properties.getProperty(name)) // 把 null转换成为 Optional.empty；
                .flatMap(OptionalUtility::stringToInt) // 把 String 转化成为 Integer， 返回一个 optional对象；
                .filter(i -> i > 0) // 过滤 int值
                .orElse(0); // 获取值 + 设置默认值
    }

    public static int readDurationNotUsingOptional(Properties properties, String name) {
        String value = properties.getProperty(name);

        if (value != null) {
            try {
                int i = Integer.parseInt(value);
                if (i > 0) {
                    return i;
                }
            } catch (NumberFormatException nfe) {

            }
        }

        return 0;
    }
}
