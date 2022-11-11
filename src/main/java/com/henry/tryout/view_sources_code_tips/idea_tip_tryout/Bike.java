package com.henry.tryout.view_sources_code_tips.idea_tip_tryout;

public class Bike implements Vehicle {

    // 从方法的实现处 跳转到 方法的声明处 - Ctrl + U
    @Override
    public void move() {
        System.out.println("脚蹬着移动");
    }
}
