package com.henry.tryout.design_pattern.proxy_pattern;

// #2 定义真实的服务类
public class RealDatabaseConnect implements DatabaseConnect{

    public RealDatabaseConnect() {
        System.out.println("初始化真实的连接");
    }

    @Override
    public void execSql(String sql) {
        System.out.println("执行SQL... : " + sql);
    }
}
