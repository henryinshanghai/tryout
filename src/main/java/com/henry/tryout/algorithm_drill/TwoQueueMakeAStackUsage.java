package com.henry.tryout.algorithm_drill;

public class TwoQueueMakeAStackUsage {
    public static void main(String[] args) {
        TwoQueueMakeAStack stack = new TwoQueueMakeAStack();

        stack.push("henry");
        stack.push("alicia");
        stack.push("jane");

        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
