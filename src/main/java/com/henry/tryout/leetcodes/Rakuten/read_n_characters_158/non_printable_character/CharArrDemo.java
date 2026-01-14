package com.henry.tryout.leetcodes.Rakuten.read_n_characters_158.non_printable_character;

// IDEA会使用 反对角线的小方格 来 表示 这里有个 不可见字符
// \u0000(Unicode码值0) 就是一个 不可见字符
public class CharArrDemo {
    public static void main(String[] args) {
        char[] chars = new char[4];

        System.out.println("初始化容量后，直接打印char数组中的各个元素");
        for (char currentChar : chars) {
            System.out.print(currentChar);
        }
    }
}
