package com.henry.tryout.design_pattern.decorator_pattern;

//#4 定义装饰器基类； 用于 把具体工作委派给 被封装的对象
public class DataSourceBaseDecorator implements DataSource{
    // 被封装的原始组件
    private DataSource originalComponent;

    public DataSourceBaseDecorator(DataSource originalComponent) {
        this.originalComponent = originalComponent;
    }

    @Override
    public void write(String data) {
        this.originalComponent.write(data);
    }

    @Override
    public String read() {
        return this.originalComponent.read();
    }
}
