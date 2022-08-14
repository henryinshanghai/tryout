package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.threadlocal_usage_02.side_effect_03;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DirtyDataInThreadLocal {
    public static ThreadLocal<String> threadLocal
            = new ThreadLocal<String>();

    public static void main(String[] args) {
        // 使用大小固定为1 的线程池 - 用于说明上一个的线程属性 会被下一个线程属性 复用
        ExecutorService pool = Executors.newFixedThreadPool(1);
        for (int i = 0; i < 2; i++) {
            // 任务线程 1, 2
            MyThread thread = new MyThread();
            // 使用线程池来执行任务 - 由于线程池复用线程，导致 threadLocal变量存储了脏数据（上个线程的数据）
            pool.execute(thread);
        }
    }

    private static class MyThread extends Thread {
        private static boolean flag = true;

        @Override
        public void run() {
            if (flag) {
                // 第一个线程在set()之后，没有进行remove()调用 线程1为 threadlocal变量ser的值为：Thread-0. session info.
                // 而第二个线程出于某种原因，没有进行 set操作
                threadLocal.set(this.getName() + ". session info.");
                flag = false;
            }

            // 结果：线程2执行到这条语句时，使用的 threadLocal的值是上一个线程设置的
            System.out.println(this.getName() + "线程是 " + threadLocal.get());
        }
    }

}
/*
结论： 在线程池中使用 ThreadLocal时，由于线程的重用，可能会导致 脏数据 - ThreadLocal变量没能是线程私有的
解决手段：每次用完 threadLocal之后，及时地调用 remove()方法完成清理。
 */