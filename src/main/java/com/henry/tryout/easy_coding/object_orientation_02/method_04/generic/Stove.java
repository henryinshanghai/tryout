package com.henry.tryout.easy_coding.object_orientation_02.method_04.generic;

// 验证：
// 使用泛型的好处：{类型安全; 提升可读性; 代码重用;}
public class Stove {
    public static <T> T heat(T food) {
        System.out.println(food + "is done");
        return food;
    }

    public static void main(String[] args) {
        // 加热肉
        Meat meat = new Meat();
        Stove.heat(meat);

        // 加热汤
        Soup soup = new Soup();
        Stove.heat(soup);
    }
}

class Meat {
}

class Soup {
}
