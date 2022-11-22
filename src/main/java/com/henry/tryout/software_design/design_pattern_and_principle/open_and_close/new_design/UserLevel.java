package com.henry.tryout.software_design.design_pattern_and_principle.open_and_close.new_design;

// 新设计中引入的模型 - 用户级别
public interface UserLevel {
    // 接口中应该有什么样的行为？ 该用户级别 对应的房间价格
    double getPrice(Room room);
}
