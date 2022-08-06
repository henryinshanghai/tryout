package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_sync_03.semaphore_sync_03;

import java.util.concurrent.Semaphore;

public class CustomCheckWindow_02 {
    public static void main(String[] args) {
        // 设置3个信号量 - 3个检票窗口
        Semaphore semaphore = new Semaphore(3);

        // 这个队伍排了5个人
        for (int i = 0; i <= 5; i++) {
            new SecurityCheckThread(i, semaphore).start();
        }
    }

    private static class SecurityCheckThread extends Thread {
        private int seq;
        private Semaphore semaphore;

        public SecurityCheckThread(int seq, Semaphore semaphore) {
            this.seq = seq;
            this.semaphore = semaphore;
        }

        public void run() {
            try {
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
                semaphore.release();
                System.out.println("No." + seq + "乘客已经完成查验。");
            }
        }

    }
}
