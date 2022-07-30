package com.henry.tryout.algorithm_drill;

import java.util.Stack;

// 使用两个栈来实现一个队列 - FIFO
public class TwoStackMakeAQueue {
    private Stack<String> stack1 = new Stack<>();
    private Stack<String> stack2 = new Stack<>();

    public void appendTail(String item) {
        stack1.push(item);
    }

    public String deleteFromHead() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }

        return stack2.pop();
    }
}
