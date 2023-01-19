package com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.nested_control_statements;

import java.util.List;

public class NestedConditionExtractForLoopBody {

    public void distributeEpubs(final long bookId) {
        List<Epub> epubs = this.getEpubByBookId(bookId);

        for (Epub epub : epubs) {
            // #1 把for循环的循环体 独立成一个方法
            // 作用：循环体中不再存在任何"影响认知的缩进"；
            this.distributeEpub(epub);
        }
    }

    private void distributeEpub(Epub epub) {
        if (epub.isValid()) {
            boolean registered = this.registerIsbn(epub);
            if (registered) {
                this.sendEpub(epub);
            }
        }
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
