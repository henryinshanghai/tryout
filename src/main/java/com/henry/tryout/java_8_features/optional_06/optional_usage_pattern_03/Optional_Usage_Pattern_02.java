package com.henry.tryout.java_8_features.optional_06.optional_usage_pattern_03;

import com.henry.tryout.java_8_features.optional_06.optional_in_java_02.Car;
import com.henry.tryout.java_8_features.optional_06.optional_in_java_02.Insurance;
import com.henry.tryout.java_8_features.optional_06.optional_in_java_02.Person;

import java.util.Optional;

// 验证：Optional类型的属性能够提供很好的语义 - 这个字段可能为null
// 验证：当方法参数是 Optional类型时，可以使用 isPresent()进行非null判断 + 使用get()方法来获取其包装的值
// 验证：什么时候该使用 flatMap(), 什么时候该使用 map() - 实践大于分析
public class Optional_Usage_Pattern_02 {
    public static void main(String[] args) {
        Person person = new Person();
        Car car = new Car();

        findCheapestInsurance(person, car);
    }

    // 找到最便宜的保险公司
    private static Insurance findCheapestInsurance(Person person, Car car) {
        // 调用不同保险公司提供的查询服务

        // 对比查询到的所有数据

        // 返回比较得到的最便宜的保险公司
        Insurance cheapestCompany = new Insurance();
        return cheapestCompany;
    }

    // 找到最便宜的保险公司 - Null安全的版本
    // 特征：这种实现方式 isPresent() 与 直接使用==号判空 没有本质区别
    public Optional<Insurance> nullSafeFindCheapestCompany01(
            Optional<Person> person,
            Optional<Car> car
    ) { // 从方法签名中就可以知道： person与car都有可能为空
        if (person.isPresent() && car.isPresent()) {
            return Optional.of(findCheapestInsurance(person.get(), car.get()));
        } else {
            return Optional.empty();
        }
    }

    // 找到最便宜的保险公司 - Null安全版本（优雅实现）
    // 特征：这种方式使用了 Optional对象的 flatMap 与 map方法 进行取值操作/转换操作
    public Optional<Insurance> nullSafeFindCheapestCompany02(
            Optional<Person> person,
            Optional<Car> car
    ) {
        // 这里的参数是一个解引用后的对象
        return person.flatMap(p ->
                                car.map(c ->
                                    findCheapestInsurance(p, c)));
    }
}
