package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.threadlocal_usage_02.InheritableThreadLocal_and_SimpleDateFormat_02;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

// 使用 ThreadLocal 来 封装 想要被线程私有的对象 - simpleDateFormat
public class SimpleDateFormatDemo {
    public static void main(String[] args) {
        /*
            SimpleDateFormat中有一个 Calendar对象；
            abstract class DateFormat {
                protected Calendar calendar;
            }

            这个 calendar对象 在 被多个线程 共享 时，很容易 发生错误；
            解决方式：使用ThreadLocal，使得 每个线程 都有 自己的calendar对象
         */
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    }

    // 使用 ThreadLocal 来 封装 想要被线程私有的对象 - simpleDateFormat
    private static final ThreadLocal<DateFormat> DATE_FORMAT_THREADLOCAL =
            new ThreadLocal<DateFormat>() {
                @Override
                protected DateFormat initialValue() {
                    return new SimpleDateFormat("yyyy-MM-dd");
                }
            };
}
