package com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.refactor_with_polymorphism;

import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.Book;
import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.Epub;

// #2-1 定义一组业务类，实现 UserLevel中定义的业务行为，并提供自己专有的逻辑
public class RegularUserLevel implements UserLevel {


    @Override
    public double getBookPrice(Book book) {
        return book.getPrice();
    }

    @Override
    public double getEpubPrice(Epub epub) {
        return epub.getPrice();
    }
}
