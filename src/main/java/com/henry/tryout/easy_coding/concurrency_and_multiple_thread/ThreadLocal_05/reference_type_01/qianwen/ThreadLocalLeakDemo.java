package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01.qianwen;

public class ThreadLocalLeakDemo {
    static class BigObject {
        byte[] data = new byte[100 * 1024 * 1024]; // 100MB

        // 作用：对象 被GC前的 回调方法，用于 清理资源（已弃用）
        // 应用：用于演示 BigObject对象 是否 被GC回收
        // 如果被回收，就会 执行此方法，打印语句
        @Override
        protected void finalize() {
            System.out.println("BigObject finalized!");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        {
            // tl 强引用 ThreadLocal对象
            ThreadLocal<BigObject> tl = new ThreadLocal<>();
            // 在 ThreadLocal对象中 封装 BigObject对象 -> 底层对应的是一个Entry对象：threadLocal实例(key弱引用) -> BigObject对象(value强引用)
            tl.set(new BigObject());
            // tl 出作用域  因此 threadLocal实例 就只存在有 弱引用，会被GC。但 BigObject对象 仍旧被 value强引用，不会被GC
            // tl.remove();
        }

        System.gc(); // 建议 GC
        Thread.sleep(1000);

        System.out.println("GC done.");
        // 你会发现：finalize() 没有被调用！
        // 因为 BigObject 仍被 ThreadLocalMap 强引用
    }
}
