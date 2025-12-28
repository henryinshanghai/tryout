package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_sync_03.semaphore_sync_02.semaphore_02;

import java.util.concurrent.Semaphore;

// 验证：可以 使用信号量 来 对多个线程的执行 进行同步
// 同步的效果：控制 同时访问某一资源 或 执行某段代码的 线程数量上限。
// 比喻：许可证 发放机；① 线程想要 执行任务，就必须 先得到 许可证；② 任务执行完成后，归还 许可证；
// 特征：Semaphore 实现的是 “限流” 或 “资源池”式的并发控制，而不是 互斥（虽然 可以 模拟互斥）；
// 应用场景：使用 信号量 来 并行处理 多个线程排队执行的情况
public class SemaphoreUsage {
    public static void main(String[] args) {
        // #1 设置 大小为3的信号量 - 3个检票窗口
        Semaphore semaphore = new Semaphore(3);

        // #2 准备 线程队伍 - 这个队伍排了5个人(线程)
        for (int i = 0; i <= 5; i++) {
            // 创建 5个线程对象(共用 同一个 只有3个窗口信号量)，并 尝试启动
            // 同步效果：信号量 会 控制并发度 只能为3 aka 只有3个线程 能够同时执行
            new SecurityCheckThread(i, semaphore).start();
        }
    }

    // 自定义线程
    private static class SecurityCheckThread extends Thread {
        private int seq;
        // 持有 信号量类型的成员变量
        private Semaphore semaphore;

        public SecurityCheckThread(int seq, Semaphore semaphore) {
            this.seq = seq;
            this.semaphore = semaphore;
        }

        public void run() {
            try {
                // #3 获取到 信号量中的一个信号(窗口)
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
                // #4 释放掉 信号量中的信号(业务办理完成，窗口被空出)
                semaphore.release();
                System.out.println("No." + seq + "乘客已经完成查验。");
            }
        }

    }
}
