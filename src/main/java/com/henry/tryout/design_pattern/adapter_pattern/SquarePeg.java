package com.henry.tryout.design_pattern.adapter_pattern;

public class SquarePeg {
    // 方形钉子的边长
    private double width;

    public SquarePeg(double width) {
        this.width = width;
    }

    // 获取到方形钉子的边长
    public double getWidth() {
        return this.width;
    }
}
