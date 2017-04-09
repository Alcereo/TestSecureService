package ru.alcereo;

import java.io.Serializable;

/**
 * Created by alcereo on 09.04.17.
 */
public class TestEntity implements Serializable{

    private static final long serialVersionUID = -7788619177798333712L;

    private String name;
    private int age;

    public TestEntity() {
    }

    public TestEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

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


    @Override
    public String toString() {
        return "TestEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
