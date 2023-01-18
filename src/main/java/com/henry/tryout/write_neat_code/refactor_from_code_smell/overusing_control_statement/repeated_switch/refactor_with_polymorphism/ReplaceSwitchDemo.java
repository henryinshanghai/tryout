package com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement.repeated_switch.refactor_with_polymorphism;


import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement.repeated_switch.Book;
import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement.repeated_switch.Epub;

// #3 使用多态特性 来 替代先前重复的switch语句
public class ReplaceSwitchDemo {
    public double getBookPrice(final User user, final Book book) {
        // 多态特性：这里的level对象的值 取决于 传入的user具体是什么类型
        UserLevel level = user.getUserLevel();
        // 具体的level 调用 getBookPrice(book) 会得到具体的金额
        return level.getBookPrice(book);
    }

    public double getEpubPrice(final User user, final Epub epub) {
        UserLevel userLevel = user.getUserLevel();
        return userLevel.getEpubPrice(epub);
    }
}
