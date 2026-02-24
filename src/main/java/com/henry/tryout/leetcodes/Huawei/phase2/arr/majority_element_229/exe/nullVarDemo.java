package com.henry.tryout.leetcodes.Huawei.phase2.arr.majority_element_229.exe;

// 验证：
// ① 当 比较 包装类型 与 基本类型 时，会 对包装类型 自动拆箱；
// ② 自动拆箱的原理：Integer a; => a.intValue();
// ③ 因此 如果包装类型变量的值 为null，就会产生 NPE。
// 使用建议：在 使用包装类型进行比较时，先判断其是否为null
public class nullVarDemo {
    public static void main(String[] args) {
        Integer a = null;
        int b = 10;
        Integer c = 100;

//        if(a == null) { // a == null 或者 null == a 不会抛出异常
//        if(b == a) { // 但 b == a会抛出NPE
        if(c == a) { // c == a 不会抛出异常
            System.out.println("a的值为null");
        } else {
            System.out.println("a的值不为null");
        }
    }
}
