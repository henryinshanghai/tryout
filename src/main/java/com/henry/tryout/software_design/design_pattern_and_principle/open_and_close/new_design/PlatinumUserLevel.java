package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.new_design;

// 新需求：添加白金会员；
// 实现手段：既然user本身持有level，只需要添加一个新的level就好
public class PlatinumUserLevel implements UserLevel {

    @Override
    public double getPrice(Room room) {
        return room.getPrice() * 0.75;
    }
}
