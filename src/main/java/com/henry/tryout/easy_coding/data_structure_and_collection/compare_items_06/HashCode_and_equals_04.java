package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06;

import java.util.HashMap;

public class HashCode_and_equals_04 {
    public static void main(String[] args) {

        HashMap<Object, Object> map = new HashMap<>();

        map.put("henry", "jane");

        // get()方法中并没有预期的if判断
        map.get("henry");
    }
}
