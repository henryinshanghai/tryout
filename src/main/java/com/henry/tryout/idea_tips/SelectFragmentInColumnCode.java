package com.henry.tryout.idea_tips;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
    验证：如果想要同时选择多个代码段，则需要：
        #1 在column模式下(使用action能够开启/关闭)，
        #2 按下 ctrl + shift + alt并保持；
        #3 然后鼠标拖拽选中
 */
public class SelectFragmentInColumnCode {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(Arrays.asList("Henry", "Alicia", "Kelly", "Annie"));

        names.add("ada");
        names.add("andy");
        names.add("adam");

        names.stream().forEach(System.out::println);

    }
}
