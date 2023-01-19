package com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.refactor_with_polymorphism;

// #3 重新定义User - 让它持有UserLevel变量，以此来调用 业务方法
public class User {
    private UserLevel level;

    public UserLevel getUserLevel() {
        return level;
    }
}
