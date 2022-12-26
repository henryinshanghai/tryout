package com.henry.tryout.design_pattern.proxy_pattern;

public class ProxyPatternClient {
    public static void main(String[] args) {

        // 创建代理对象，并绑定到 服务接口类型上
        DatabaseConnect connect = new DatabaseConnectProxy();
        connect.execSql("SELECT 1");
    }
}
