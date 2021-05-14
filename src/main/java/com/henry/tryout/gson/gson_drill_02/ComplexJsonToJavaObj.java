package com.henry.tryout.gson.gson_drill_02;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ComplexJsonToJavaObj {
    public static void main(String[] args) {

        Person henry = new Person(1, "henry", "anhui");

//        Gson gson = new Gson();
        // pretty print
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        // 2. Java object to JSON string
        // simply object
        String json = gson.toJson(henry);
        System.out.println(json);

        // complex object
        Staff staff = createStaffObject();
        String staffJsonStr = gson.toJson(staff);
        System.out.println(staffJsonStr);


    }

    /**
     * 创建一个staff对象 - 包含各种类型的属性
     * @return
     */
    private static Staff createStaffObject() {

        Staff staff = new Staff();

        staff.setName("mkyong");
        staff.setAge(35);
        staff.setPosition(new String[]{"Founder", "CTO", "Writer"});
        Map<String, BigDecimal> salary = new HashMap() {{
            put("2010", new BigDecimal(10000));
            put("2012", new BigDecimal(12000));
            put("2018", new BigDecimal(14000));
        }};
        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("java", "python", "node", "kotlin"));

        return staff;

    }
}
