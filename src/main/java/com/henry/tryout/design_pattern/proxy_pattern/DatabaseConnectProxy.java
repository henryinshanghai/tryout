package com.henry.tryout.design_pattern.proxy_pattern;

// #3 定义代理类 - 特征：由代理类，自行决定自己代理的是什么真实服务
public class DatabaseConnectProxy implements DatabaseConnect{
    // 1 持有真实服务对象/被代理类的引用
    private DatabaseConnect databaseConnect;

    public DatabaseConnectProxy() {
        System.out.println("初始化代理类...");
    }

    // 执行代理方法
    @Override
    public void execSql(String sql) {
        // 执行真实服务对象前的操作
        beforeExec();

        /* 需要时才初始化， 相当于懒加载 */
        if (this.databaseConnect == null) {
            // 初始化 真实服务的对象
            this.databaseConnect = new RealDatabaseConnect();
        }

        // 把工作委派给 真实服务对象
        this.databaseConnect.execSql(sql);

        // 执行后的一些处理操作
        afterExec();
    }

    // 比如，转换字段等
    private void afterExec() {
        System.out.println("执行SQL后...");
    }

    // 比如，记录日志
    private void beforeExec() {
        System.out.println("执行SQL前...");
    }


}
