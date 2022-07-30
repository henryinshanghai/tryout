package com.henry.tryout.algorithm_drill;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TwoQueueMakeAStack {
    private Queue<String> queue1 = new ArrayBlockingQueue<String>(10);
    private Queue<String> queue2 = new ArrayBlockingQueue<String>(10);

    // 考虑两个队列是否为空的情况 - 因为队列模拟栈的过程会使其中一个队列为空队列
    public void push(String item) {
        if (queue1.isEmpty()) {
            queue2.add(item);
            return;
        }

        if (queue2.isEmpty()) {
            queue1.add(item);
        }
    }

    public String pop() {
        if (queue1.isEmpty() && queue2.isEmpty()) {
            try {
                throw new Exception("stack is empty");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (!queue1.isEmpty()) {
            while (queue1.size() != 1) {
                queue2.add(queue1.poll());
            }

            return queue1.poll();
        }

        if (!queue2.isEmpty()) {
            while (queue2.size() != 1) {
                queue1.add(queue2.poll());
            }
            return queue2.poll();
        }

        return null;
    }
}
