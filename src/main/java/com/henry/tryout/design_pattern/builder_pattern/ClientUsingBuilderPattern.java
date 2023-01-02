package com.henry.tryout.design_pattern.builder_pattern;

public class ClientUsingBuilderPattern {
    public static void main(String[] args) {
        // 车子建造者
        CarBuilder carBuilder = new CarBuilder();

        // 把建造者作为参数，创建出 指挥者
        Director director = new Director(carBuilder);
        // 使用指挥者 生产车子*1
        director.constructCityCar();
        System.out.println("cityCar: " + carBuilder.getResult());

        // 使用指挥者 生产车子*2
        director.constructSuvCar();
        System.out.println("suvCar: " + carBuilder.getResult());

        // 车子说明书
        CarManualBuilder manualBuilder = new CarManualBuilder();
        // 把说明书作为参数，传给 director的设值方法
        director.changeBuilder(manualBuilder);
        // 使用更新后的指挥者生产车子 * 1
        director.constructCityCar();
        System.out.println("cityCarManual: " + manualBuilder.getResult());

        // 使用更新后的指挥者生产车子 * 1
        director.constructSuvCar();
        System.out.println("cityCarManual: " + manualBuilder.getResult());
    }
}
