package com.henry.tryout.java_concurrency_program.theory_foundation.JMM_02;


class VolatileExample {
    int x = 0;
    // 保证变量v 对所有线程的可见性
    volatile boolean v = false;

    public void writer() {
        x = 42;
        v = true;
    }

    public void reader() {
        if (v == true) {
            // 这里x会是多少呢？
        }
    }
}