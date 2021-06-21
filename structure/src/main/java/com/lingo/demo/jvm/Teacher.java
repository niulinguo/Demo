package com.lingo.demo.jvm;

public class Teacher {
    public static final String SEX_MAN = "man";
    public static String SEX_WOMAN = "woman";

    private String name;
    private int age;
    private String sex;

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
