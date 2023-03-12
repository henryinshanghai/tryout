package com.henry.tryout.idea_tips.today;

// 验证：使用快捷键 Alt + F7 能够触发 IDEA的find功能，从而光标所在的symbol所有出现的地方
public class locateOccurrencesDemo {
    public static void main(String[] args) {
        String name = "henry";

        if (name.equals("henry")) {
            System.out.println("name is henry");
        } else {
            name = "alicia";
            System.out.println("name is alicia");
        }
    }
}
