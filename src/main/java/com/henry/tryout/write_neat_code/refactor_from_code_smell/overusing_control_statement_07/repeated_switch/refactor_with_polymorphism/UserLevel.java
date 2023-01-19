package com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.refactor_with_polymorphism;

import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.Book;
import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.Epub;

// #1 添加缺少的模型：用户等级
public interface UserLevel {
    /* 定义 会受业务等级影响的业务行为 */
    double getBookPrice(Book book);
    double getEpubPrice(Epub epub);
}
