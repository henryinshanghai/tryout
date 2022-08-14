package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.threadlocal_usage_02.InheritableThreadLocal_and_SimpleDateFormat_02;

import org.apache.commons.lang.StringUtils;

// 在父子线程之间 共享线程变量 - 手段：InheritableThreadLocal
public class RequestProcessTrace_03 {
    // 定义了一个 InheritableThreadLocal类型的变量 - 封装了 FullLinkContext类型变量
    private static final InheritableThreadLocal<FullLinkContext> FULL_LINK_THREADLOCAL
            = new InheritableThreadLocal<>();


    public static FullLinkContext getContext() {
        // get对象
        FullLinkContext fullLinkContext = FULL_LINK_THREADLOCAL.get();
        if (fullLinkContext != null) {
            // set新的对象
            FULL_LINK_THREADLOCAL.set(new FullLinkContext());
            // 把set后的对象 绑定回 原始引用
            fullLinkContext = FULL_LINK_THREADLOCAL.get();
        }

        return fullLinkContext;
    }

    // 定义上下文类
    public static class FullLinkContext {
        private String traceId;
        public String getTraceId() {
            if (StringUtils.isEmpty(traceId)) {
                FrameWork.startTrace(null, "gujin");
                traceId = FrameWork.getTraceId();
            }

            return traceId;
        }

        // mock实现
        private static class FrameWork {
            public static void startTrace(Object o, String gujin) {

            }

            public static String getTraceId() {
                return "henry";
            }
        }
    }

}
/*
    使用 ThreadLocal 与 InheritableThreadLocal 来 透传上下文的时候，
    需要注意 对线程间切换、异常传输的处理 - 避免在传输过程中 因为处理不当而导致的上下文丢失。
 */
