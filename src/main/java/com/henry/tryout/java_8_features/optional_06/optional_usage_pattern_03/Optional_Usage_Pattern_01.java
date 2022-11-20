package com.henry.tryout.java_8_features.optional_06.optional_usage_pattern_03;

import com.henry.tryout.java_8_features.optional_06.optional_in_java_02.Car;
import com.henry.tryout.java_8_features.optional_06.optional_in_java_02.Insurance;
import com.henry.tryout.java_8_features.optional_06.optional_in_java_02.Person;

import java.util.Optional;

// 验证：创建Optional对象的几种方式 - {Optional.empty(), Optional.of(car), Optional.ofNullable(car1)}
// 验证：从Optional对象中获取其封装信息的几种方式 - {optInsurance.map(Insurance::getName), .flatMap(), {get(), orElse(), orElseGet(), ifPresent()}}
public class Optional_Usage_Pattern_01 {
    public static void main(String[] args) {

        // 演示：在client代码中，创建一个 Optional对象的多种方式；
        Optional<Car> optionalCar = createAnOptionalObject();

        // 演示：从Optional对象中安全地获取其封装的对象
        getInformationViaOptional();

    }

    private static void getInformationViaOptional() {
        // 从optional对象中获取信息 - 手段：optional对象的map方法；
        // 特征：返回的是一个 optional对象。相当于 stream中的map操作
        getInformationUsingMapMethod();

        // 链接多个 optional对象，从最后面的optional对象中获取信息
        getInformationUsingSequentialOptional();

        // 从optional对象中获取信息的几种方式 - get(), orElse(), orElseGet(), ifPresent()
        getInformationFromOptional();
    }

    private static void getInformationFromOptional() {
        Person person = new Person();
        Optional<Person> optionalPerson = Optional.ofNullable(person);

        Person person1 = optionalPerson.get();

        Person person2 = optionalPerson.orElse(new Person());

        Person person3 = optionalPerson.orElseGet(() -> new Person());

        optionalPerson.ifPresent(Person::getCar);

    }

    private static void getInformationUsingSequentialOptional() {
        Person person = new Person();
        Optional<Person> optPerson = Optional.of(person);

        // 尝试连续调用map()方法 - 结果：编译报错
        // 原因：调用getCar()之后，会得到嵌套的optional对象 - Optional对象可以像Stream一样视为一个元素集合
        /*

            Optional<String> name = optPerson.map(Person::getCar)
                    .map(Car::getInsurance)
                    .map(Insurance::getName);
         */
        // 手段：flatMap()方法；
        String insuranceName = getCarInsuranceName(optPerson);

    }

    private static String getCarInsuranceName(Optional<Person> person) { // 方法可以接受一个空值
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName) // Insurance::getName()的返回值类型是 String,所以这里使用的是map()方法
                .orElse("Unknown"); // optional对象的 取值方式 - 如果 optional的值为空，设置默认值
    }

    private static void getInformationUsingMapMethod() {
        // 创建 optional对象
        Insurance insurance = new Insurance();
        Optional<Insurance> optInsurance = Optional.ofNullable(insurance);

        // 从 optional对象中获取信息 - 手段：optional对象.map()方法
        Optional<String> name = optInsurance.map(Insurance::getName);
        System.out.println("name: " + name);
    }

    private static Optional<Car> createAnOptionalObject() {
        // 创建一个空的Optional对象 - 手段： 静态工厂方法 empty()
        Optional<Car> optionalCar1 = Optional.empty();

        // 使用一个 非空值来创建一个 Optional对象 - 手段： 静态方法 Optional.of()
        Car car = new Car();
        Optional<Car> optionalCar2 = Optional.of(car);

        // 创建一个允许接受null作为参数的 Optional对象 - 手段： 静态方法 ofNullable()
        Car car1 = null;
        Optional<Car> optionalCar3 = Optional.ofNullable(car1); // 如果car1变量是null，则：方法会返回一个空的Optional对象。

        return Optional.empty();
    }

}
