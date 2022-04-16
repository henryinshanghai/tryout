package com.henry.tryout.java_8_features.refactor_test_debug_04.improve_codes_readability_01;

public class from_anonymous_class_to_Lambda_expression_01 {
    public static void main(String[] args) {
        usingLambdaReplaceAnonymousClass();
    }

    private static void usingLambdaReplaceAnonymousClass() {
        simple_case();

        complex_case();
    }

    private static void complex_case() {
        // Ⅰ this变量的含义的区分 - 对匿名内部类，this表示类自身； 对Lambda表达式，this表示的是 其包含类；

        // Ⅱ 变量作用域的区分 - 匿名内部类能够屏蔽 其包含类中的变量； Lambda表达式没有这个特性
        difference_of_variable_scope();

        // Ⅲ Lambda表达式可能导致 晦涩难懂的代码
        difference_of_code_readability();

    }

    private static void difference_of_code_readability() {
        // 使用 匿名类 来 传入参数
        doSomething(new Task() {
            @Override
            public void execute() {
                System.out.println("execute method called!!!");
            }
        });

        // 使用lambda表达式 来 传入参数 - 这会导致编译器不知道该去执行哪一个方法 aka 一个编译报错
        // 手段：强制类型转换
        doSomething((Task) () -> System.out.println("xxx"));
    }


    // #condition1 描述符相同的抽象方法
    interface Task {
        public void execute(); // 这个函数式接口中的抽象方法的描述符 和 Runnable中run()方法的描述符是一样的
    }

    // #condition2 重载的多个同名方法
    public static void doSomething(Runnable runnable) {
        runnable.run();
    }

    public static void doSomething(Task task) {
        task.execute();
    }

    private static void difference_of_variable_scope() {
        int a = 10;
        Runnable r1 = () -> {
//            int a = 2; // 编译错误： 当前scope中已经存在了变量a
            System.out.println(a);
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                int a = 2;
                System.out.println(a);
            }
        };
    }

    private static void simple_case() {
        // 使用 lambda表达式来取代匿名类
        // 使用匿名类 - 手段：new <接口类型>
        Runnable hello = new Runnable() {

            @Override
            public void run() {
                System.out.println("Hello");
            }
        };

        // 使用lambda表达式 - 特征：这样一个 lambda表达式 所代表的函数描述符 能够匹配到很多的 函数式接口中的抽象方法
        // Runnable就是它所能匹配到的 函数式接口中的一个
        Runnable hello2 = () -> System.out.println("Hello");
    }
}
