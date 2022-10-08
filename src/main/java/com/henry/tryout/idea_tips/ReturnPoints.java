package com.henry.tryout.idea_tips;

// 验证：在某一个方法的return语句上 按下Ctrl + Shift + F7,就能高亮显示所有的方法返回点
public class ReturnPoints {
    public static void main(String[] args) {
        String name = pickName(1);
        System.out.println("the name you pick is: " + name);
    }

    private static String pickName(int index) {
        if (index == 1) {
            return "Henry"; // Ctrl + shift + F7 + (Fn)
        } else if (index == 2) {
            return "Alicia";
        } else {
            return "Jingjing";
        }
    }
}
