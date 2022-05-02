package com.henry.tryout.java_8_features.optional_06.deal_with_null_01;

public class client_code_01 {
    public static void main(String[] args) {
        Person henry = new Person();

        String insuranceName_0 = getCarInsuranceName(henry);

        String insuranceName_1 = avoidNPEWithIfs(henry);

        String insuranceName_2 = avoidNPEWithReturns(henry);
    }



    // 手段2：在每一个进行null检查的位置，如果为null，则添加一个退出点
    // 特征：代码的退出点太多，容易遗漏 代码难以维护
    private static String avoidNPEWithReturns(Person person) {
        if (person == null) {
            return "Unknown";
        }

        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }

        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }

        return insurance.getName();
    }

    // 手段1：每一次取值之前，都使用if做一次判空
    // 特征：太多的判断语句，甚至淹没了真正有用的业务代码
    private static String avoidNPEWithIfs(Person person) {
        if (person != null) {
            Car car = person.getCar();

            if (car != null) {
                Insurance insurance = car.getInsurance();
                if (insurance != null) {
                    return insurance.getName();
                }
            }
        }

        return "Unknown";
    }


    private static String getCarInsuranceName(Person person) {
        // 这里可能会有NPE的风险 - 人没有车,车没上保险
        return person.getCar().getInsurance().getName();
    }
}
/*
    不应该使用 null 来 表示变量值的缺失
 */
