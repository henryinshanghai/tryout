package com.henry.tryout.easy_coding.data_structure_and_collection.map_08.hashMap_03;

import java.util.HashMap;

// 没能够形成预期中的死链
public class HashMapEndlessLoop {
    private static HashMap<Long, EasyCoding> map = new HashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            (new Thread(){
                public void run() {
                    map.put(System.nanoTime(), new EasyCoding());
                }
            }).start();

        }
    }
}

