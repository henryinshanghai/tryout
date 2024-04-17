package com.henry.tryout.design_pattern.adapter_pattern;

public class ClientUsingAdapter {
    public static void main(String[] args) {

        // 服务类 - 预期接收 目标类
        RoundHole roundHole = new RoundHole(5);
        // 目标类
        RoundPeg roundPeg = new RoundPeg(5);

        if (roundHole.isFit(roundPeg)) {
            System.out.println("圆形钉子能够 打进 圆形孔中");
        }

        // 需要被适配的类
        SquarePeg smallSquarePeg = new SquarePeg(2);
        SquarePeg bigSquarePeg = new SquarePeg(20);

        // 用于 适配“待适配类”的适配器
        AdapterForSquarePeg adapterForSmallSquarePag = new AdapterForSquarePeg(smallSquarePeg);
        AdapterForSquarePeg adapterForBigSquarePeg = new AdapterForSquarePeg(bigSquarePeg);

        // 最终目标：客户端代码能够 在本应该使用 目标类的地方，就可以直接使用 适配器类
        if (roundHole.isFit(adapterForSmallSquarePag)) {
            System.out.println("宽度为2的方形钉子 能够打进 半径为5的圆形孔中");
        }

        if (!roundHole.isFit(adapterForBigSquarePeg)) {
            System.out.println("宽度为20的方形钉子 不能够打进 半径为5的圆形孔中");
        }
    }
}
