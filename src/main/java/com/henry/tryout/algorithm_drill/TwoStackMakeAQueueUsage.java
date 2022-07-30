package com.henry.tryout.algorithm_drill;

public class TwoStackMakeAQueueUsage {
    public static void main(String[] args) {

        TwoStackMakeAQueue queue = new TwoStackMakeAQueue();
        queue.appendTail("henry");
        queue.appendTail("alicia");
        queue.appendTail("jane");

        System.out.println(queue.deleteFromHead()); // henry
        System.out.println(queue.deleteFromHead()); // alicia
        System.out.println(queue.deleteFromHead()); // jane
    }
}
