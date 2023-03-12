package com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.refactor_with_polymorphism;


import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.original.Book;
import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.original.Epub;

// 业务：售出纸质书（Book）与电子书（Epub）时，其价格根据 “购买者的等级” 不同会有不同的折扣
// 组件设计：设计一个 UserLevel的接口模型，用于 汇总所有 “会受用户等级影响的业务行为”
// 特征：相比于 original，业务类明显增多
// #4 使用接口&实现类(aka 多态特性) 来 替代switch语句, 来实现 “根据业务条件，选择执行对应的业务动作”
public class ReplaceSwitchWithPolyDemo {

    public double getBookPrice(final User user, final Book book) {
        // 多态特性：这里的level对象的值 取决于 运行时传入的user具体是什么类型
        UserLevel level = user.getUserLevel();
        // 具体的level 调用 getBookPrice(book) 会得到具体的金额
        return level.getBookPrice(book);
    }

    public double getEpubPrice(final User user, final Epub epub) {
        UserLevel userLevel = user.getUserLevel();
        return userLevel.getEpubPrice(epub);
    }
}
