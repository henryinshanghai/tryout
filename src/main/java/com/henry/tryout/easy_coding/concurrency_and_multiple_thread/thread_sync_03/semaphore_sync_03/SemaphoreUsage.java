package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_sync_03.semaphore_sync_03;

import java.util.concurrent.Semaphore;

// 使用信号量 来 同步多个线程
// 场景：使用信号量 来 并行处理多个线程排队执行的情况
public class SemaphoreUsage {
    public static void main(String[] args) {
        // #1 设置大小为3的信号量 - 3个检票窗口
        Semaphore semaphore = new Semaphore(3);

        // #2 准备线程队伍 - 这个队伍排了5个人(线程)
        for (int i = 0; i <= 5; i++) {
            new SecurityCheckThread(i, semaphore).start();
        }
    }

    // 自定义线程
    private static class SecurityCheckThread extends Thread {
        private int seq;
        // 持有信号量类型的成员变量
        private Semaphore semaphore;

        public SecurityCheckThread(int seq, Semaphore semaphore) {
            this.seq = seq;
            this.semaphore = semaphore;
        }

        public void run() {
            try {
                // #3 获取到信号量中的一个信号(窗口)
                semaphore.acquire();
                System.out.println("No." + seq + "乘客，正在查验中...");

                // 可疑人员 - 业务逻辑：把编号整除2的人视为可疑人员
                if (seq % 2 == 0) {
                    Thread.sleep(1000);
                    System.out.println("No." + seq + "乘客，身份可疑，不能出国！");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                // #4 释放掉信号量中的信号
                semaphore.release();
                System.out.println("No." + seq + "乘客已经完成查验。");
            }
        }

    }
}
