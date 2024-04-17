package com.henry.tryout.design_pattern.adapter_pattern;

// #4 适配器类 - 用于把需要被适配的类，适配成为 目标类
// 方形钉子的一个适配器，用来 适配圆形的孔
public class AdapterForSquarePeg extends RoundPeg{ // #1 继承自 “适配到的对象（圆形孔）的接口”
    // 持有 被适配的组件 - 方形钉子
    private SquarePeg squarePeg;

    // 构造方法中，传入方形的钉子
    public AdapterForSquarePeg(SquarePeg squarePeg) {
        this.squarePeg = squarePeg;
    }

    // 获取适配之后，方形钉子的“半径”
    @Override
    public double getRadius() {
        return (this.squarePeg.getWidth() * Math.sqrt(2)) / 2;
    }
}
