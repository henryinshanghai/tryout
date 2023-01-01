package com.henry.tryout.design_pattern.adapter_pattern;

public class ClientUsingAdapter {
    public static void main(String[] args) {

        /* 圆形孔 与 圆形钉子 */
        RoundHole roundHole = new RoundHole(5);
        RoundPeg roundPeg = new RoundPeg(5);

        if (roundHole.isFit(roundPeg)) {
            System.out.println("圆形钉子能够 打进 圆形孔中");
        }

        /* 方形钉子 与 圆形孔 */
        SquarePeg smallSquarePeg = new SquarePeg(2);
        SquarePeg bigSquarePeg = new SquarePeg(20);

        AdapterForSquarePeg adapterForSmallSquarePag = new AdapterForSquarePeg(smallSquarePeg);
        AdapterForSquarePeg adapterForBigSquarePeg = new AdapterForSquarePeg(bigSquarePeg);

        if (roundHole.isFit(adapterForSmallSquarePag)) {
            System.out.println("宽度为2的方形钉子 能够打进 半径为5的圆形孔中");
        }

        if (!roundHole.isFit(adapterForBigSquarePeg)) {
            System.out.println("宽度为20的方形钉子 不能够打进 半径为5的圆形孔中");
        }
    }
}
