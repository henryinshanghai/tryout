package com.henry.tryout.design_pattern.adapter_pattern;

public class RoundPeg {
    // 钉子的半径
    private double radius;

    // 构造方法
    public RoundPeg() {

    }

    public RoundPeg(double radius) {
        this.radius = radius;
    }

    // 获取钉子的半径大小
    public double getRadius() {
        return this.radius;
    }
}
