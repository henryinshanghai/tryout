package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.thread_pool_04.source_code_02;

import javafx.concurrent.Worker;

import java.util.Optional;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPoolStatus {
    // Integer的32位： 右边的29位表示 工作线程数， 左边的3位表示线程池状态
    // 3个二进制数字能够表示 0-7一共8个数值
    public static final int COUNT_BITS = Integer.SIZE - 3; // Ⅰ

    // 类似于 子网掩码 用来指示 某些位上数字 所表示的具体含义
    public static final int COUNT_MASK = (1 << COUNT_BITS) - 1;

    // 使用 最左边的三位数 来 表示 线程的5种状态
    // 手段：用 COUNT_BITS 做 必要的位运算
    // 作用：这个状态 表示 线程池 能够接收 新的任务
    public static final int RUNNING = -1 << COUNT_BITS;

    // 作用：这个状态 表示 线程池 不会再接收 新任务，但是 会继续执行 队列中的待执行任务
    public static final int SHUTDOWN = 0 << COUNT_BITS;

    // 作用：这个状态表示 全面拒绝； 特征：会中断正在处理的任务
    public static final int STOP = 1 << COUNT_BITS;

    // 这个状态 表示 所有的任务都 已经被终止
    public static final int TIDYING = 2 << COUNT_BITS;

    // 这个状态 表示 已经清理完成现场
    public static final int TERMINATED = 3 << COUNT_BITS;
    private ReentrantLock mainLock;

    // 与运算 掩码取反(得到左边3位的001) 表示线程池 当前处在 STOP状态
    public static int runStateOf(int c) {
        return c & ~COUNT_MASK;
    }

    // 掩码操作，得到 右边的29位 aka 工作线程数量
    private static int workerCountOf(int c) {
        return c & COUNT_MASK;
    }

    // 把 左边3位 与 右边29位 按照 异或运算，合并成为 一个值
    private static int ctlOf(int rs, int wc) {
        return rs | wc;
    }

    /*
        线程池的参数
     */
    int corePoolSize;
    int maximumPoolSize;
    long keepAliveTime;
    TimeUnit unit;
    BlockingQueue<Runnable> workQueue;
    ThreadFactory threadFactory;
    RejectedExecutionHandler handler;

    Optional<Integer> ctl;

    // execute()方法的实现 - 作用：使用者 使用execute()方法 向 线程池 提交任务
    public void execute(Runnable command) {
        // 获取 线程数量、线程池状态 - 手段：Integer类型
        int c = ctl.get();

        // 如果 工作线程 小于 核心线程数量，则：创建新线程 并 执行任务
        if (workerCountOf(c) < corePoolSize) {
            // addWorker 是 另一个非常重要的方法
            if (addWorker(command, true)) { // Ⅲ-①
                return;
            }
            // 如果 创建失败，则：为防止 外部已经 在线程池中 加入了 新任务，这里 重新获取一次
            c = ctl.get();
        }

        // 如果 线程池 处于 RUNNING状态，则/才会：把 新任务 添加到 队列 中
        if (isRunning(c) && workQueue.offer(command)) {
            int recheck = ctl.get();
            // 如果 线程池 不是 RUNNING的状态，则：把 刚刚加入到队列的任务 移除掉
            if (!isRunning(recheck) && remove(command)) {
                reject(command);
            }
            // 如果 之前的线程 已经被消费完，则：新建一个线程
            else if (workerCountOf(recheck) == 0) {
                addWorker(null, false); // Ⅲ-②
            }
        }
        // 如果：核心线程 与 队列 都已经满了的话，尝试创建 一个新的线程
        else if (!addWorker(command, false)) { // Ⅲ-③
            // 如果addWorker 返回的是 false, 说明 创建失败，则：唤醒 拒绝策略
            reject(command);
        }
    }

    private void reject(Runnable command) {

    }

    private boolean remove(Runnable command) {
        return false;
    }

    private boolean isRunning(int c) {
        return c < SHUTDOWN;
    }

    /*
        根据 当前线程池的状态，检查 是否可以 添加新的任务线程
            如果 可以，则：创建 新线程，并启动。返回true
            如果 返回false，则表示：①  线程池 没有处在 RUNNING的状态； ② 线程工厂 创建新的线程 失败

        firstTask 从外部启动 线程池 时，所需要创建的 第一个线程。
        core：新增工作线程时 的 判断依据；
            true： 表示 新增工作线程 时，需要判断 当前RUNNING状态的线程 数量 是不是少于 corePoolSize
            false：表示 新增工作线程 时，需要判断 当前RUNNING状态的线程数量 是不是小于 maximumPoolSize
     */

    private boolean addWorker(Runnable firstTask, boolean core) {
        // 第一处：不需要任务预定义的语法标签，响应下面的continue。 retry用于快速退出多重嵌套循环
        retry:
        for (int c = ctl.get(); ; ) {
            /*
                RUNNING < SHUTDOWN < STOP < TIDYING < TERMINATED
                第二处：
                如果是 RUNNING的状态，则：条件为false - 不需要执行后继的判断
                如果是 STOP及以上的状态 或者 firstTask不为空 或者 队列为空，则：直接返回创建新线程失败
             */
            boolean isNotAllowedToCreateTask =
                    runStateAtLeast(c, SHUTDOWN) &&
                            (runStateAtLeast(c, STOP) || firstTask != null || workQueue.isEmpty());

            if (isNotAllowedToCreateTask) {
                return false;
            }

            for (; ; ) {
                // 如果 超过了最大允许线程数，则：不允许再创建新的线程了
                // 最大线程数不能超过 2^29, 否则会影响到 左3位的线程池状态表示
                if (workerCountOf(c) >=
                        ((core ? corePoolSize : maximumPoolSize) & COUNT_MASK)) {
                    return false;
                }

                // 第三处： 把当前的活动线程数量 + 1
                // 模式：把要做的操作放到if()子句中
                // 特征：compareAndIncrementWorkerCount()有可能失败，这时候需要重试
                if (compareAndIncrementWorkerCount(c)) {
                    break retry;
                }

                // 线程池状态 和工作线程数量 是可变化的 - 因此需要经常提取这个最新之
                c = ctl.get();
                // 第四处： 如果已经是关闭状态，则：再次从 retry标签进入，在第二处再做判断
                if (runStateAtLeast(c, SHUTDOWN))
                    continue retry;

                // 第五处： 如果 线程仍旧处于 RUNNING的状态，则：说明只是在第Ⅲ处失败
                // 继续循环执行
            }
        }

        // 开始创建工作线程
        boolean workerStarted = false;
        boolean workerAdded = false;

        Worker w = null;
        /*
        try {
            // 使用 Worker构造方法中的线程池工厂 来 创建线程，然后封装成为工作线程 Worker对象
            w = new ThreadPoolExecutor.Worker(firstTask);
            // 第六处：获取worker中的属性对象 thread
            final Thread t = w.thread;

            if (t != null) {
                // 在 进行 ThreadPoolExecutor的敏感操作时，都需要持有主锁 - 以此避免在添加与启动线程时被干扰到
                final ReentrantLock mainLock = this.mainLock;
                mainLock.lock();
                try {
                    int c = ctl.get();
                    // 当线程池状态位 RUNNING或者SHUTDOWN，且 firstTask初始线程为空时,则：
                    if (isRunning(c) || (runStateLessThan(c, STOP)
                            && firstTask == null)) {
                        workers.add(w);
                        int s = workers.size();
                        // 整个线程池在运行期间的最大 并发任务数量
                        if(s > largestPoolSize) largestPoolSize = s;
                        workerAdded = true;
                    }
                } finally {
                    mainLock.unlock();
                }

                if (workerAdded) {
                    // 工作线程成功添加，调用start()方法来启动线程
                    // 注意这里的线程 不是 线程池execute()方法的参数 所指向的线程
                    t.start();
                    workerStarted = true;
                }
            }
        } finally {
            if (!workerStarted) {
                // 如果线程启动失败，则：把Ⅲ处加上的工作线程计数 再减回去
                addWorkerFailed(w);
            }
        }
         */

        return workerStarted;
    }

    private boolean runStateLessThan(int c, int stop) {
        return false;
    }

    private boolean compareAndIncrementWorkerCount(int c) {
        return false;
    }

    private boolean runStateAtLeast(int c, int shutdown) {
        return false;
    }
}

/*
    Ⅰ 线程池的状态使用高3位表示 - 包含符号位；
    2 五种状态的十进制数值从小到大依次为：
        RUNNING < SHUTDOWN < STOP < TIDYING < TERMINATED
        作用： 通过比较值的大小，就可以确定出 线程池的状态。
        应用：
            isRunning判断；
            private static boolean isRunning(int c) {
                return c < SHUTDOWN;
            }

    Ⅲ execute()方法中，有三次尝试 addWorker()的操作
    引起拒绝策略的原因有两个：
        #1 线程池的状态不是 RUNNING；
        #2 等待队列已经满员了

 */