package com.henry.tryout.view_sources_code_tips.idea_tip_tryout;

public class VehicleMoveDemo {
    public static void main(String[] args) {
        Vehicle plane = new Plane();
        // 从方法的使用处 跳转到 方法的具体实现处 - Ctrl + ALt + B
        plane.move();
    }
}
