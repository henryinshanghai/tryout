package com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.nested_control_statements.refactor;

import java.util.List;

// 重构#2：使用卫语句（Guard Clauses）来 取代嵌套的条件表达式（Conditional）
public class NestedConditionUsingGuardClauses {

    public void distributeEpubs(final long bookId) {
        List<Epub> epubs = this.getEpubByBookId(bookId);

        for (Epub epub : epubs) {
            // 重构#1 把for循环的循环体 独立成一个方法
            // 作用：循环体中不再存在任何"影响认知的缩进"；
            this.distributeEpub(epub);
        }
    }

    // 重构#2：使用卫语句（Guard Clauses）来 取代嵌套的条件表达式（Conditional）
    private void distributeEpub(Epub epub) {
        // 卫语句 - 就是在flag为false时，提前返回的语句
        if (!epub.isValid()) {
            return;
        }

        boolean registered = this.registerIsbn(epub);
        if (!registered) {
            return;
        }
        this.sendEpub(epub);
    }

    private void sendEpub(Epub epub) {
        System.out.println("sending out epub: " + epub);
    }

    private boolean registerIsbn(Epub epub) {
        return true;
    }

    private List<Epub> getEpubByBookId(long bookId) {
        return null;
    }
}
