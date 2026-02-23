package com.henry.tryout.leetcodes.Huawei.phase1.str.is_subsequence_392.exe;

// 验证：对一个空字符串 调用 charAt(0) 会得到一个 ‘字符串索引越界异常’
public class emptyStrDemo {
    public static void main(String[] args) {
        String emptyStr = "";

        System.out.println(emptyStr.length()); // 0
        System.out.println(emptyStr.charAt(0)); // String index out of range: 0
    }
}
