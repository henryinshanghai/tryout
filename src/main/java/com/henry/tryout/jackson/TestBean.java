package com.henry.tryout.jackson;

import java.util.List;

public class TestBean {
    private int id;
    private String name;
    private List<Element> elements;

    public TestBean() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", elements=" + elements +
                '}';
    }
}

class Element {
    private int age;
    private String elName;

    public Element() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getElName() {
        return elName;
    }

    public void setElName(String elName) {
        this.elName = elName;
    }

    @Override
    public String toString() {
        return "Element{" +
                "age=" + age +
                ", elName='" + elName + '\'' +
                '}';
    }
}
