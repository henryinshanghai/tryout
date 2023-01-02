package com.henry.tryout.design_pattern.builder_pattern;

// #3-1 具体生成器（Concrete Builders）用来 提供构造过程的具体实现；
public class CarBuilder implements Builder{
    // 持有 产品Car本身
    private Car result;

    @Override
    public void reset() {
        this.result = new Car();
    }

    @Override
    public void buildCarType(CarType carType) {
        this.result.setCarType(carType);
    }

    @Override
    public void buildSeats(int num) {
        this.result.setSeats(num);
    }

    @Override
    public void buildEngine(Engine engine) {
        this.result.setEngine(engine);
    }

    // 获取车子产品
    public Car getResult() {
        return this.result;
    }
}
