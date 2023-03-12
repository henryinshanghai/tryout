package com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.refactor_with_polymorphism;

import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.original.Book;
import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.original.Epub;

// #1 添加缺少的模型
// 模型的行为/能力 - 汇总所有 因为用户等级不同而会受到影响的业务行为
public interface UserLevel {
    /* 定义 会受用户等级影响的业务行为 */
    double getBookPrice(Book book);
    double getEpubPrice(Epub epub);
}
