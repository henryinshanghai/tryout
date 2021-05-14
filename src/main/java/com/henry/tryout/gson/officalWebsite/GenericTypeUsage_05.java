package com.henry.tryout.gson.officalWebsite;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class GenericTypeUsage_05 {
    public static void main(String[] args) {
        Gson gson = new Gson();
        Foo<Bar> foo = new Foo<Bar>();
        foo.value = new Bar("henry");
//        String json = gson.toJson(foo);// May not serialize foo.value correctly
//
//        System.out.println(json);

//        Foo foo1 = gson.fromJson(json, foo.getClass());// Fails to deserialize foo.value as Bar
//        System.out.println(foo1.toString());

        Type fooType = new TypeToken<Foo<Bar>>() {}.getType();
        String json = gson.toJson(foo, fooType);
        System.out.println(json);

        Foo<Bar> foo1 = (Foo<Bar>)gson.fromJson(json, fooType);
        System.out.println(foo1.toString());
    }
}


class Foo<T> {
    T value;

    @Override
    public String toString() {
        return "Foo{" +
                "value=" + value +
                '}';
    }
}

class Bar {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Bar() {

    }

    public Bar(String name) {
        this.name = name;
    }
}