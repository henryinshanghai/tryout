package com.henry.tryout.easy_coding.concurrency_and_multiple_thread.ThreadLocal_05.reference_type_01;

public class House {

    public static final Integer DOOR_NUMBER = 2000;
    // 大的数组
    public Door[] doors = new Door[DOOR_NUMBER];

    private String info;

    public House() {

    }

    public House(String s) {
        this.info = s;
    }

    class Door {
    }
}
