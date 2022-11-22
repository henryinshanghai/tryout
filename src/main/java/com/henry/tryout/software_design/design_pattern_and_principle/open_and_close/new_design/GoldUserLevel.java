package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.new_design;

public class GoldUserLevel implements UserLevel {

    @Override
    public double getPrice(final Room room) {
        return room.getPrice() * 0.8;
    }
}
