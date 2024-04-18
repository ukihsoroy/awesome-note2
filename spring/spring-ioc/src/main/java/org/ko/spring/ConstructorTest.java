package org.ko.spring;

public class ConstructorTest {

    private String name;

    private Short age;

    public ConstructorTest(String name, Short age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Short getAge() {
        return age;
    }
}
