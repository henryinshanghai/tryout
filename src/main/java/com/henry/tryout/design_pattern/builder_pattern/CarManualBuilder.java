package com.henry.tryout.design_pattern.builder_pattern;

// #3-2 具体生成器（Concrete Builders）用来 提供构造过程的具体实现；
public class CarManualBuilder implements Builder {
    // 持有 产品说明书 Manual
    private Manual result;

    @Override
    public void reset() {
        this.result = new Manual();
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

    // 获取 车子说明书
    public Manual getResult() {
        return this.result;
    }
}
