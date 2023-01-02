package com.henry.tryout.design_pattern.decorator_pattern;

import java.util.Base64;

// #6 把#4的装饰器基类，扩展成为 具体的装饰类；   用于增强原始对象
public class EncryptionDecorator extends DataSourceBaseDecorator {

    // 构造器
    public EncryptionDecorator(DataSource originalComponent) {
        // 调用父类的构造器
        super(originalComponent);
    }

    @Override
    public void write(String data) {
        // 添加编码操作
        super.write(encode(data));
    }

    @Override
    public String read() {
        // 添加解码操作
        return decode(super.read());
    }

    // 加密操作
    private String encode(String data) {
        return Base64.getEncoder().encodeToString(data.getBytes());
    }

    // 解密方法
    private String decode(String read) {
        return new String(Base64.getDecoder().decode(read.getBytes()));
    }

}
