package com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.nested_control_statements.original;

import java.util.List;

// 代码坏味道：for循环中，嵌套if语句
public class NestedConditionDemo {

    public void distributeEpubs(final long bookId) {
        List<Epub> epubs = this.getEpubByBookId(bookId);
        for (Epub epub : epubs) {
            if (epub.isValid()) {
                boolean registered = this.registerIsbn(epub);
                if (registered) {
                    this.sendEpub(epub);
                }
            }
        }
    }

    private void sendEpub(Epub epub) {
        System.out.println("send out epub: " + epub);
    }

    private boolean registerIsbn(Epub epub) {
        return true;
    }

    private List<Epub> getEpubByBookId(long bookId) {
        return null;
    }
}

