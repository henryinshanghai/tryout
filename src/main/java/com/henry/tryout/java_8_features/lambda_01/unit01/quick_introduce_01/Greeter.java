package com.henry.tryout.java_8_features.lambda_01.unit01.quick_introduce_01;

public class Greeter {

    // 使用 接口类型的参数 - 可以向一个方法中，传递另外一个方法/action
    public void greet(Greeting greeting) {
        greeting.perform();
    }

    public static void main(String[] args) {
        // 01 创建实例对象
        Greeter greeter = new Greeter();

        // 02 创建 接口类型实现类的对象 - 作为greet()方法的参数
        HelloWorldGreeting helloWorldGreeting = new HelloWorldGreeting();

        /* 03 现在有很多种方式能够执行 方法所表示的action */
        greeter.greet(helloWorldGreeting); // Hello World!  注：这种方式，程序员 需要定义接口 + 实现类；
        helloWorldGreeting.perform(); // Hello World! 这是 直接在实例上调用实例方法；

        /* 现在尝试用其他的方式 来 表示&传递action */
        // 1 匿名内部类 - 手段：直接new接口类型
        Greeting innerClassGreeting = new Greeting() {
            @Override
            public void perform() {
                System.out.println("Hello World!");
            }
        };
        greeter.greet(innerClassGreeting); // Hello World!

        // 2 使用lambda表达式 来 对方法进行简化
        /* 可以舍弃的部分： 访问修饰符、返回值类型、方法名、参数列表中的参数类型 */
        // () -> System.out.println("Hello World!");
        /* 这样得到的lambda表达式/一个action 应该怎么使用？
            1 直接以一个整体作为方法的参数 来传递 - 什么类型的方法参数能够接收它？
            2 绑定到一个变量上，以便在需要的时候对lambda表达式进行复用 - 应该绑定到什么类型的变量上？
            答：使用 ‘函数式接口类型’； 请见：Greeting.java 中添加的注解

            特征：1 接口类型 被@FunctionInterface注解 修饰；
            2 接口类型中的抽象方法 要和 lambda表达式的各个要素(参数列表、返回值类型)相匹配

            对于 () -> System.out.println("Hello World!"); 这个lambda表达式，
            她所需要的抽象方法满足： 参数列表为空，返回值类型为void - perform()抽象方法就满足呀
         */
        Greeting greeting = () -> System.out.println("Hello World!");
        greeter.greet(greeting); // Hello World!

    }
}
/*
启示：
    传递action的几种方式：
        1 实例对象的方法；
        2 匿名内部类中的方法实现；
        3 lambda表达式 & 相关的接口类型与抽象方法；
 */
