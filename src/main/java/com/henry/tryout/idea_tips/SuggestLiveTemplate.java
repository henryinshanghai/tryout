package com.henry.tryout.idea_tips;

import java.util.Arrays;
import java.util.List;

// 使用 Ctrl + J能够得到动态代码模板缩写的提示列表
public class SuggestLiveTemplate {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("henry", "jianmei", "jimmy");

        // 按下Ctrl + J - IDEA就能建议出inn的缩写
        if (names != null) {
            System.out.println("names list is not null");
        }
    }
}
