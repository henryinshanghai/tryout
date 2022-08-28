package com.henry.tryout.java_concurrency_program.theory_foundation.bug_roots_01;


public class Singleton {
    static Singleton instance;

    static Singleton getInstance(){
        if (instance == null) {
            synchronized(Singleton.class) {
                if (instance == null) {
                    // new实例对象的指令: 在堆中划出一块内存区域、把内存区域的首地址赋值给instance、对内存区域进行初始化操作；
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
