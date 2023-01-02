package com.henry.tryout.design_pattern.builder_pattern;

// #2 生成器/建造器（Builder） 用来 声明构造产品的 通用步骤
public interface Builder {

    // 重置
    void reset();

    // 车子类型
    void buildCarType(CarType carType);

    // 安装座位
    void buildSeats(int num);

    // 安装发动机
    void buildEngine(Engine engine);
}


