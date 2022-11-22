package com.henry.tryout.software_design.design_pattern_and_principle.single_responsibility;

// 原始的用户类
// 验证：这个User类其实承载了逻辑上不同的概念
public class MixedUser {
    // 修改密码
    void changePassword(String password) {

    }

    // 加入一个项目
    void joinProject(Project project) {

    }

    // 接管一个项目，成为管理员
    void takeOverProject(Project project) {

    }

    // 新增需求*1：设置用户的电话号码
    void changePhoneNumber(PhoneNumber phoneNumber) {

    }

    // 新增需求*2：查看用户参与了多少个项目
    int countProject() {
        return 10;
    }
}

class Project {

}

class PhoneNumber {

}
