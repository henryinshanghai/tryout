package com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.refactor_with_polymorphism;

import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.original.Book;
import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.original.Epub;

// #2 定义一组业务类，实现 UserLevel中定义的业务行为，并提供自己专有的逻辑
// #2-3 黄金用户
public class GoldUserLevel  implements UserLevel {
    @Override
    public double getBookPrice(Book book) {
        return book.getPrice() * 0.8;
    }

    @Override
    public double getEpubPrice(Epub epub) {
        return epub.getPrice() * 0.85;
    }

}
