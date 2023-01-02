package com.henry.tryout.design_pattern.decorator_pattern;

// #2 归类出基础组件 与 可选组件之间的通用方法。并使用一个组件接口 来 声明这些个方法；
public interface DataSource {

    // 写入数据
    void write(String data);

    // 读取数据
    String read();
}
