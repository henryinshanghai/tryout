package com.henry.tryout.design_pattern.adapter_pattern;

// #3 预期接收 目标类的服务类
public class RoundHole {
    // 孔的半径
    private double radius;

    // 有参数的构造方法
    public RoundHole(double radius) {
        this.radius = radius;
    }

    // 判断钉子是否能够打入孔中？
    public boolean isFit(RoundPeg roundPeg) {
        return this.radius >= roundPeg.getRadius();
    }
}
