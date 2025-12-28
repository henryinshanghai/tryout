package com.henry.tryout.easy_coding.object_orientation_02.method_04.override;

import java.sql.SQLClientInfoException;
import java.sql.SQLException;

// 验证：在子类中 覆写父类中方法的一些规则 - 一大两小两同
public class Father {
    // 访问权限：位于包外的 当前类的子类
    protected Number doSomething(int a, Integer b, Object c) throws SQLException {
        System.out.println("Father's doSomething");
        return new Integer(7);
    }
}

class Son extends Father {
    /**
     * 1.权限扩大：由 protected -> public(一大)
     * 2.返回值类型 是 父类方法返回值类型 的子类（两小）
     * 3.抛出异常的类型 是 父类方法抛出异常类型 的子类
     * 4.方法名必须严格一致（两同）
     * 5.参数类型及个数 必须严格一致
     * 6.必须添加@Override - 用于提供 编译期的检查
     */
    public Integer doSomething(int a, Integer b, Object c)
            throws SQLClientInfoException {
        if (a == 0) {
            throw new SQLClientInfoException();
        }

        return new Integer(17);
    }
}
