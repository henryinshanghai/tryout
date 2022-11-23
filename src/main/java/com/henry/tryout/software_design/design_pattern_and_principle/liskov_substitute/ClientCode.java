package com.henry.tryout.software_design.design_pattern_and_principle.liskov_substitute;

// 验证：违反Liskov替换原则的示例
// ReportHandler 与 NotificationHandler 都继承自 Handler，但是两者对Handler做了自己的扩展 -> 因此无法完全代替父类
// 结论：任何使用了 RTTI的代码，很有可能就已经破坏了Liskov替换原则
public class ClientCode {

    // 对于handler参数，进行多个操作 显然违反开闭原则
    void handle(final Handler handler) {
        /* 对于参数handler，在运行时识别出它的具体类型 手段：运行时类型识别RTTI */
        if (handler instanceof ReportHandler) {
            // 如果是A类型的实例，则：调用report() 来 生成报告
            ((ReportHandler) handler).report();
        }

        if (handler instanceof NotificationHandler) {
            // 如果是B类型的实例，则：调用sendNotification() 来 发送通知
            ((NotificationHandler) handler).sendNotification();
        }
    }
}

class Handler {

}

class ReportHandler extends Handler{
    void report() {

    }
}

class NotificationHandler extends Handler {
    void sendNotification() {

    }
}
