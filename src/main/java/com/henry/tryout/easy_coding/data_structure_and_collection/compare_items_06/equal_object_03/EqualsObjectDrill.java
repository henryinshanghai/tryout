package com.henry.tryout.easy_coding.data_structure_and_collection.compare_items_06.equal_object_03;


import com.google.common.base.Objects;

import java.util.HashSet;
import java.util.Set;

public class EqualsObjectDrill {
    private int id;
    private String name;

    public EqualsObjectDrill() {
    }

    public EqualsObjectDrill(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;

        EqualsObjectDrill that = (EqualsObjectDrill) obj;
        return id == that.id && Objects.equal(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name); // 对于相同的输入，总会产生相同的输出
//        return 100;
    }

    public static void main(String[] args) {
        Set<Object> set = new HashSet<>();
        set.add(new EqualsObjectDrill(1, "henry"));
        set.add(new EqualsObjectDrill(1, "henry"));
        set.add(new EqualsObjectDrill(1, "henry"));

        System.out.println("set's size = " + set.size());
    }
}
