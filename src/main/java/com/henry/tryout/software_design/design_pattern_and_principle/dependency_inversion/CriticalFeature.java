package com.henry.tryout.software_design.design_pattern_and_principle.dependency_inversion;

// 验证：典型代码结构所引入的问题 - 高层模块 会依赖于 低层模块
// 高层类：CriticalFeature 低层模块：{Step1, Step2}
public class CriticalFeature {
    private Step1 step1;
    private Step2 step2;

    void run() {
        // 执行第一步
        step1.execute();
        // 执行第二部
        step2.execute();
    }
}

class Step1 {
    void execute() {

    }
}

class Step2 {
    void execute() {

    }
}