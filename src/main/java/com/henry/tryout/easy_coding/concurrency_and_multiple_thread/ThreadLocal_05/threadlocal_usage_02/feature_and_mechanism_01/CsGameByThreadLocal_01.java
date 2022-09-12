package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.threadlocal_usage_02.feature_and_mechanism_01;

import java.util.concurrent.ThreadLocalRandom;

// 使用 ThreadLocal 来 让每个线程都有自己的变量副本
// 手段： 定义 ThreadLocal类型的成员变量，然后在线程的run()方法中使用它们。
public class CsGameByThreadLocal_01 {
    // 静态成员变量 - 被类的所有实例共享
    public static final Integer BULLET_NUMBER = 1500;
    public static final Integer KILLED_ENIMIES = 0;
    public static final Integer LIFE_VALUE = 10;
    public static final Integer TOTAL_PLAYERS = 10;

    /*
        随机数；  作用：用来展示每个对象的不同数据???
        特征：1 静态变量 - 被所有实例共享 2 JDK7引入 - 每个线程都能有自己的随机数生成器，而不是共享
     */
    private static final ThreadLocalRandom RANDOM
            = ThreadLocalRandom.current();

    /*
         初始化子弹的数量
         手段：定义一个 ThreadLocal类型的变量 - #1 new ThreadLocal并重写其 initialValue()方法
         作用：相当于对 静态变量BULLET_NUMBER 做了一层封装

            特征：1 每个线程都会单独持有 ThreadLocal变量的副本；
            2 由于变量没有被多个线程共享，因此不存在线程安全问题；
            3 通常会使用 private static来修饰 ThreadLocal变量；
     */
    private static final ThreadLocal<Integer> BULLET_NUMBER_THREADLOCAL
            = new ThreadLocal<Integer>() {

        @Override
        protected Integer initialValue() {
            // 这里封装的是一个 不可变对象 - Integer
            return BULLET_NUMBER;
        }
    };

    // 初始化杀敌数量 - 使用 ThreadLocal变量
    private static final ThreadLocal<Integer> KILLED_ENEMIES_THREADLOCAL
            = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return KILLED_ENIMIES;
        }
    };

    // 初始化自己的命数 - 使用 ThreadLocal变量
    private static final ThreadLocal<Integer> LIFE_VALUE_THREADLOCAL
            = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return LIFE_VALUE;
        }
    };

    // 定义每位队员（线程） - 线程类 继承自 Thread, 重写run()方法
    private static class Player extends Thread {

        // 在线程中所使用的变量：
        /*
            RANDOM
            BULLET_NUMBER_THREADLOCAL
            BULLET_NUMBER
            KILLED_ENEMIES_THREADLOCAL
            TOTAL_PLAYERS
            LIFE_VALUE_THREADLOCAL
            LIFE_VALUE
         */
        @Override
        public void run() {
            // 从 Threadlocal对象上调用 get() - 得到对象中封装的值
            Integer bullets = BULLET_NUMBER_THREADLOCAL.get() -
                    RANDOM.nextInt(BULLET_NUMBER);
            Integer killEnemies = KILLED_ENEMIES_THREADLOCAL.get() +
                    RANDOM.nextInt(TOTAL_PLAYERS);
            Integer lifeValue = LIFE_VALUE_THREADLOCAL.get() -
                    RANDOM.nextInt(LIFE_VALUE);

            System.out.println(getName() + ", BULLET_NUMBER is " + bullets);
            System.out.println(getName() + ", KILLED_ENEMIES is " + killEnemies);
            System.out.println(getName() + ", LIFE_VALUE is " + lifeValue + "\n");

            BULLET_NUMBER_THREADLOCAL.remove();
            KILLED_ENEMIES_THREADLOCAL.remove();
            LIFE_VALUE_THREADLOCAL.remove();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < TOTAL_PLAYERS; i++) { // 每次循环都会新建线程
            // 每个线程对象 使用的 ThreadLocal变量都是自己私有的副本 - ThreadLocal变量的值不会相互干扰
            new Player().start();
        }
    }
}
/*
#问题1 ThreadLocal变量是怎么成为 线程的独立拷贝 的？
    - 1 我们在定义 ThreadLocal变量时，覆写了其中的 initialValue()方法；
    - 2 initialValue这个方法的执行时机是 - threadLocal对象调用get()方法时。

    get()源码：
        public T get() {
            Thread t = Thread.currentThread(); // 当前线程
            ThreadLocalMap map = getMap(t); // 当前线程的 ThreadLocalMap对象

            if (map != null) { // 如果map不为null,说明 Thread类的threadLocals属性已经被初始化 Key是ThreadLocal变量
                ThreadLocalMap.Entry e = map.getEntry(this);
                // 如果e为null,则： setInitialValue()方法还是会被执行到
                if (e != null) {
                    @SuppressWarnings("unchecked")
                    T result = (T)e.value; // value是一个泛型变量，具体类型取决于使用者传入的类型
                    return result;
                }
            }

            // map为null时,直接执行setInitialValue(),并将结果返回
            return setInitialValue();
        }

    setInitialValue()源码：
        protected T initialValue() {
            return null;
        }

        private T setInitialValue() {
            // initialValue()方法 特征：1 protected方法; 2 在创建 ThreadLocal对象时一般会覆写 initialValue()
            T value = initialValue();
            Thread t = Thread.currentThread();

            // getMap(t)做的事情 - 返回线程t的 threadLocals属性 return t.threadLocals;
            ThreadLocalMap map = getMap(t);
            if (map != null)
                map.set(this, value); // 这个value是 使用者在定义ThreadLocal变量时传入的
            else
                createMap(t, value);
            return value;
        }

ThreadLocal中封装可变对象的demo：InitValueInThreadLocal
 */
