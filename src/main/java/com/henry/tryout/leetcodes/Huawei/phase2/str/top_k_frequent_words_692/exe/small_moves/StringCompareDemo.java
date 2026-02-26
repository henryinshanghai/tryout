package com.henry.tryout.leetcodes.Huawei.phase2.str.top_k_frequent_words_692.exe.small_moves;

/*
验证：字符串对象的compareTo()方法 会 返回一个数字 来 表示返回结果
    正数 表示 第一个字符串 大于 第二个字符串；
    负数 表示 第一个字符串 小于 第二个字符串；
    0 表示 第一个字符串 等于 第二个字符串；
应用：自定义排序、去重、找范围等；
与equals()的比较：
    ① equals() 是为了“判同”，一旦不同 就“拉黑”；
    ② compareTo() 是为了“排位”，一旦发现不同 就要 “算出差值” 来 决定座次。
 */
public class StringCompareDemo {
    public static void main(String[] args) {
        String henry = "henry";
        String jack = "jack";

        System.out.println("henry 与 jack的比较结果为："
                + henry.compareTo(jack)); // -2

        String ada = "ada";
        System.out.println("henry 与 ada的比较结果为："
                + henry.compareTo(ada)); // 7

        String henry2 = "henry";
        System.out.println("henry 与 henry的比较结果为："
                + henry.compareTo(henry2));
    }
}
