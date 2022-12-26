package com.henry.tryout.design_pattern.proxy_pattern;

// #1 定义服务接口
public interface DatabaseConnect {

    // 执行 SQL
    void execSql(String sql);
}
