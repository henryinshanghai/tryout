package com.henry.tryout.async_program.via_future_in_JDK_02.jdk_stream_and_completableFuture_06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class listProcess_command_vs_statement_01 {
    // Person类型
    static class Person {
        private String name;
        private int age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }

    // 创建 person对象的列表
    public static List<Person> makeList() {
        List<Person> personList = new ArrayList<Person>();
        Person p1 = new Person();
        p1.setAge(10);
        p1.setName("zlx");
        personList.add(p1);

        p1 = new Person();
        p1.setAge(12);
        p1.setName("jiaduo");
        personList.add(p1);

        p1 = new Person();
        p1.setAge(5);
        p1.setName("ruoran");
        personList.add(p1);
        return personList;
    }

    // 处理person列表中的元素 - 命令式的编码方式
    public static void noStream(List<Person> personList) {

        List<String> nameList = new ArrayList<>();

        for (Person person : personList) {
            if (person.age >= 10) {
                nameList.add(person.getName());
            }
        }

        for (String name : nameList) {
            System.out.println(name);
        }

    }

    // 处理person列表中的元素 - 对集合的stream进行处理（声明式的编码方式）
    public static void useStream(List<Person> personList) {

        List<String> nameList = personList.stream()
                .filter(person -> person.getAge() >= 10)// 1.过滤大于等于10的
                .map(person -> person.getName())// 2.使用map映射元素
                .collect(Collectors.toList());// 3.收集映射后元素

        // note：想要再次使用stream进行处理时，必须要再次获取到 stream对象
        nameList.stream().forEach(name -> System.out.println(name));
    }

    public static void main(String[] args) {

        List<Person> personList = makeList();

        noStream(personList);

        System.out.println("--- line ---");

        useStream(personList);

    }
}
/*
note:
    stream 与 no stream的方式，打印结果相同。
    但是：
        1 编码方式不一样；
        2 处理效率不一样；

    对于stream的方式，在调用最终的方法之前，其他的方法调用都不会执行。
    一声号起，大家才会开始干活

 */
