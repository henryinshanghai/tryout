package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_sync_03.concept_01.volatile_03;

// 验证：多线程条件下，使用双重检查锁的方式，无法得到有效的唯一实例
public class LazyInitDemo {
    private static TransactionService service = null;

    public static TransactionService getService() {
        if (service == null) {
            synchronized (LazyInitDemo.class) {
                if (service == null) {
                    // new对象的操作 本身 不是一个 原子操作
                    /*
                        new的三个步骤 - 这三个步骤 可能会 被CPU重新排列
                        #1 在内存中 开辟一块区域；
                        #2 调用 初始化方法，完成 对内存区域的初始化；
                        #3 把 内存区域的首地址 绑定到 变量上。

                        问题的产生：线程A 产生了一个 没有被初始化的service，而 线程B 直接取用了 这个service对象
                     */
                    service = new TransactionService();
                }
            }
        }

        return service;
    }

    public static void main(String[] args) {
        TransactionService service = getService();
        TransactionService service1 = getService();

        if (service == service1) { // 单线程条件下，双重检查锁的方式 能够得到 有效的单例
            System.out.println("单例模式使用成功！TransactionService类唯一的实例是： " + service);
        }
    }
}

class TransactionService {

}
