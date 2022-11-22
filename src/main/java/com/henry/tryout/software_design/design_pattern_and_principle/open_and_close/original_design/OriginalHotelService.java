package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.original_design;

public class OriginalHotelService {

    // 获取房间价格 - 对于不同级别的用户，房间的价格是不一样的
    public double getRoomPrice(final OriginalUser user, final Room room) {
        double price = room.getPrice();

        // 黄金用户
        if (user.getLevel() == Level.GOLD) {
            return price * 0.8;
        }

        // 白银用户
        if (user.getLevel() == Level.SILVER) {
            return price * 0.9;
        }

        // 新增需求:白金用户 - 基于现有的设计，就只能在这里添加一个新的if分支来处理新需求
        // 不符合开闭原则 - 对新增开放，对修改关闭
        // 为了符合开闭原则，则需要：修改设计，预留出 用来新增代码的扩展点
        if (user.getLevel() == Level.PLATINUM) {
            return price * 0.75;
        }

        return price;
    }
}

class OriginalUser {
    private Level level;

    public Level getLevel() {
        return level;
    }
}

class Room {
    private double price;

    public double getPrice() {
        return price;
    }
}
