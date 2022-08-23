package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06.equal_object_03;

import java.util.HashMap;

public class MapsGetMethod {
    public static void main(String[] args) {

        HashMap<Object, Object> map = new HashMap<>();

        map.put("henry", "jane");

        // get()方法中并没有预期的if判断 😳
        map.get("henry");
    }
}
