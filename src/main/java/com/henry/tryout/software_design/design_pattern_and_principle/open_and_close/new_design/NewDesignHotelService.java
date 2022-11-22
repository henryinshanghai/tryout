package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.new_design;


// 基于新的设计所编写出来的client代码 - 新的设计符合开闭原则，能够 添加新代码来应对新需求
public class NewDesignHotelService {
    public double getRoomPrice(final NewDesignUser user, final Room room) {
        return user.getRoomPrice(room);
    }
}

class NewDesignUser {
    private UserLevel level;

    public double getRoomPrice(final Room room) {
        // level接口中的抽象方法getPrice(), 接收Room类型作为参数, 返回房间的价格
        return level.getPrice(room);
    }
}

class Room {
    private double price;

    public double getPrice() {
        return price;
    }
}


