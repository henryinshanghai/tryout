package com.henry.tryout.write_neat_code.refactor_from_code_smell.overusing_control_statement_07.repeated_switch.original;

// 示例：getBookPrice() 与 getEpubPrice()方法中，出现了类似的代码
// 重复的switch，通常都是因为缺少了一个模型; - 这里是用户级别的
// 重构手段：使用多态（Polymorphism） 来 取代条件表达式（Conditional）
public class RepeatedSwitchDemo {

    // 计算书的价格
    public double getBookPrice(final User user, final Book book) {
        double price = book.getPrice();
        // 根据用户等级 来 计算最终售价
        switch (user.getLevel()) {
            // 这里使用 枚举类的item时，不能带枚举类类名
            case SILVER:
                return price * 0.9;
            case GOLD:
                return price * 0.8;
            case PLATINUM:
                return price * 0.75;
            default:
                return price;
        }
    }

    // 计算电子书的价格
    public double getEpubPrice(final User user, final Epub epub) {
        double price = epub.getPrice();
        switch (user.getLevel()) {
            case SILVER:
                return price * 0.95;
            case GOLD:
                return price * 0.85;
            case PLATINUM:
                return price * 0.8;
            default:
                return price;
        }
    }
}
