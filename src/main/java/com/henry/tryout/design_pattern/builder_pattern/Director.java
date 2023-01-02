package com.henry.tryout.design_pattern.builder_pattern;

// #4 指挥者（Director）用来定义/规定 产品构造步骤的顺序；
public class Director {
    // 持有 建造者builder - 这里持有的是接口类型
    private Builder builder;

    // 初始化指挥者 - 传入builder参数
    public Director(Builder builder) {
        this.builder = builder;
    }

    // 改造建造者 - 传入builder参数
    public void changeBuilder(Builder builder) {
        this.builder = builder;
    }

    // 按照特定步骤 来 构造轿车
    public void constructCityCar() {
        builder.reset();
        builder.buildCarType(CarType.CITY_CAR);
        builder.buildSeats(5);
        builder.buildEngine(new Engine(2.0, false));
    }

    // 按照特定步骤 来 构造SUV
    public void constructSuvCar() {
        builder.reset();
        builder.buildCarType(CarType.SUV);
        builder.buildSeats(5);
        builder.buildEngine(new Engine(3.0, false));
    }
}
