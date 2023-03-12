package com.henry.tryout.java_8_features.optional_06.avoidNPEInTrainWreck_05;

import java.util.Optional;

// 验证：对于火车残骸式的代码，可以使用 Optional.ofNullable() + map()方法来避免 “连续调用时产生NPE”
public class usingOptionalToAvoidNPEInTrainWreck {
    public static void main(String[] args) {
        Person henry = new Person("henry");

        // 可能会抛出NPE
        System.out.println(henry.getPet().getCategory().getNaming());

        // 使用 Optional ofNullable() + map() + orElse() 来 避免NPE
        Optional.ofNullable(henry)
                .map(Person::getPet)
                .map(Pet::getCategory)
                .orElse(new Category("object"));
    }
}
