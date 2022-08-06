package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_sync_03;

public class LazyInitDemo {
    private static TransactionService service = null;

    public static TransactionService getService() {
        if (service == null) {
            synchronized (LazyInitDemo.class) {
                if (service == null) {
                    // new对象本身不是一个原子操作
                    /*
                        new的三个步骤 - 这三个步骤可能会被重排
                        #1 在内存中开辟一块区域；
                        #2 调用初始化方法，完成对内存区域的初始化；
                        #3 把内存区域的首地址 绑定到 变量上。

                        问题的产生：线程A产生了一个没有被初始化的service，而线程B直接取用了这个service对象
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

        if (service == service1) {
            System.out.println("单例模式使用成功！");
        }
    }
}

class TransactionService {

}
