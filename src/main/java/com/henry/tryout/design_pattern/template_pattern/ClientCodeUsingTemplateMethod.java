package com.henry.tryout.design_pattern.template_pattern;

// #3 模板方法模式的client code - 特征：使用 父类类型的变量(NetWork) 来 调用模板方法
// 验证：模板方法中的步骤 = {父类中的默认步骤 + 子类中实现/重写的步骤}
public class ClientCodeUsingTemplateMethod {
    public static void main(String[] args) {
        NetWork qqNetwork = new QQNetWork();
        NetWork wechatNetwork = new WeChatNetwork();

        // 在子类的实例对象上，调用 模板方法/算法
        qqNetwork.contactFriends("hello QQ.");
        System.out.println("-------------------");
        wechatNetwork.contactFriends("hello WeChat.");
    }
}
