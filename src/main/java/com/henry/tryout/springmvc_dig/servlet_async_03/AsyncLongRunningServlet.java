package com.henry.tryout.springmvc_dig.servlet_async_03;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.AsyncContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

// 验证：进行”异步编程“的四个步骤
// #1 开启对异步编程的支持 - 手段：设置 @WebServlet的asyncSupported属性值为true；
// #2 获取异步处理的上下文对象 - 手段：req.startAsync(request, response)
// #3 开始进行”异步处理“ - 手段：start()方法 特征：此方法调用后，主线程就结束了。任务会交给 AsyncContext中的Executor来处理
// #4 异步处理完成后，关闭工作线程 - 手段：complete()方法
@WebServlet(name = "AsyncServlet1",
urlPatterns = "/asyncServlet1",
asyncSupported = true) // 表示 ”支持异步处理“
public class AsyncLongRunningServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(AsyncLongRunningServlet.class);

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) {
        long st = System.currentTimeMillis();
        logger.info("主线程： " + Thread.currentThread() + "-" + System.currentTimeMillis() + "-start");

        // 启动异步处理 - 手段：调用 req.startAsync(), 以获取到 “异步处理上下文对象”AsyncContext
        AsyncContext asyncContext = request.startAsync(request, response);

        // 执行异步处理 - 手段：调用 上下文对象的start()方法
        // 特征：调用start()方法之后，主线程就结束了。 Runnable会由 AsyncContext内部的Executor来处理
        asyncContext.start(() -> {
            long stSon = System.currentTimeMillis();
            System.out.println("子线程： " + Thread.currentThread() + "-" + System.currentTimeMillis() + "-start");
            try {
                // 休眠10秒钟，模拟耗时的业务
                TimeUnit.SECONDS.sleep(10);

                // 这里是子线程，请求在这里被处理了？
                asyncContext.getResponse().getWriter().write("okay");

                // 调用complete()方法 - 表示请求已经处理完成
                asyncContext.complete();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();
            }
            System.out.println("子线程： " + Thread.currentThread() + "-" + System.currentTimeMillis() + "-end, 耗时(ms): " + (System.currentTimeMillis() - stSon));

        });

        System.out.println("主线程: " + Thread.currentThread() + "-" + System.currentTimeMillis() + "-end, 耗时（ms）: " + (System.currentTimeMillis() - st));
    }
}
