package com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement.repeated_switch.refactor_with_polymorphism;

import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement.repeated_switch.Book;
import com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement.repeated_switch.Epub;

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
