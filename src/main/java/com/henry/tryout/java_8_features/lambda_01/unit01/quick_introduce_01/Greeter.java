package com.henry.tryout.java_8_features.lambda_01.unit01.quick_introduce_01;

// 验证：使用 lambda表达式 能够省去 创建实例 & 在实例上调用方法 的 步骤 - 直接执行到方法中的核心代码
// 目标：执行到perform()方法中的代码块；
// 手段1：定义接口 + 创建实现类，并提供方法实现 + 在demo中实例化具体类,并通过实例调用具体方法
// 手段2：不再创建实现类，而是在demo中直接使用匿名内部类
// 手段3：使用lambda表达式 来 替代方法体，并直接作为参数传给greet()方法以执行 - 语法简洁、语义清晰
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

        /* 手段1：实例化具体类型 */
        greeter.greet(helloWorldGreeting); // Hello World!  注：这种方式，程序员 需要定义接口 + 实现类；
        helloWorldGreeting.perform(); // Hello World! 这是 直接在实例上调用实例方法；

        /* 手段2：使用匿名内部类 */
        Greeting innerClassGreeting = new Greeting() {
            @Override
            public void perform() {
                System.out.println("Hello World!");
            }
        };
        greeter.greet(innerClassGreeting); // Hello World!

        /* 手段3：使用lambda表达式 */
        /* 可以舍弃的部分： 访问修饰符、返回值类型、方法名、参数列表中的参数类型 */
        // lambda表达式： () -> System.out.println("Hello World!");
        /* lambda表达式能够作为一等公民使用 👇
            1 作为方法的实际参数 来传递 - 如果方法形参是匹配的接口类型，则：编译就会通过
            2 绑定到一个变量上，以便在需要的时候对lambda表达式进行复用 - 如果变量的类型 与 lambda表达式相匹配，则：编译就会通过
                变量的类型需要使用 ‘函数式接口类型’；
                    demo：Greeting.java 中添加的注解
                    特征：
                        1 接口类型 被@FunctionInterface注解 修饰；
                        2 接口类型中的抽象方法 要和 lambda表达式的各个要素(参数列表、返回值类型)相匹配 - 方法不重要
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
