package com.youximao.anew.myapplication;

/**
 * Created by mengfantao on 18/7/11.
 */

public class Person {
    public String type;
    public String name;
    public int age;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Person(String type, String name, int age) {
        this.type = type;
        this.name = name;
        this.age = age;
    }

    public Person() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
