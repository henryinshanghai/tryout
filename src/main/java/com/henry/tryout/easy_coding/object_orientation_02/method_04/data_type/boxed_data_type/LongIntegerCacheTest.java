package com.henry.tryout.easy_coding.object_orientation_02.method_04.data_type.boxed_data_type;

// 验证：包装类型 相比于 基础类型的好处之一 - 高频区间 数据缓存
// 高频区间数据缓存：对于 指定区间内的赋值，由cache产生，会复用 已有的对象；
// #0 ==判断 是 对 对象地址是否相等 的判断；equals()判断 是 对 对象的值是否相等 的判断[推荐使用]；
// #1 Long类型的缓存范围 是 -128~127;
// #2 Integer类型的缓存范围 是-128~127，但它的范围 可以通过VM参数 来 修改。
public class LongIntegerCacheTest {
    public static void main(String[] args) {

        // Long类型的缓存范围 是 -128~127
        Long a = 127L;
        Long b = 127L;
        System.out.println("Long max cached value is 127, "
                + "and the result is:" + (a == b)); // 127是会被缓存的数据，因此两个变量是同一个对象，结果为 true

        Long a1 = 128L;
        Long b1 = 128L;
        System.out.println("Long=28 cache is " + (a1 == b1)); // 128 不是 会被缓存的数据，因此是两个对象，结果为 false

        Long c = -128L;
        Long d = -128L;
        System.out.println("Long min cached value is -128, " +
                "and the result is: " + (c == d)); // -128 是 会被缓存的数据，因此是同一个对象，结果为 true

        Long c1 = -129L;
        Long d1 = -129L;
        System.out.println("Long=-129 cache is " + (c1 == d1)); // -128 不是 会被缓存的数据，因此是两个对象，结果为 false

        // Long类型 只缓存 -128~127之间的数值
        Long e = 1000L;
        Long f = 1000L;
        System.out.println("Long=1000 is " + (e == f)); // 1000 不是 会被缓存的数据，因此是两个对象，结果为 false

        // Integer 是 唯一一个 可以修改缓存范围 的包装类
        // 手段：在VM options中添加参数 -XX:AutoBoxCacheMax=7777
        Integer x = 1001;
        Integer y = 1001;
        System.out.println("Integer=1001 is " + (x == y)); // false -> true
    }
}
