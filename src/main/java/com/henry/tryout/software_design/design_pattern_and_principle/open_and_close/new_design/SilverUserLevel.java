package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.new_design;

public class SilverUserLevel implements UserLevel{

    @Override
    public double getPrice(Room room) {
        return room.getPrice() * 0.9;
    }
}
