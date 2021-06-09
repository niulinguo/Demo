package com.lingo.demo.typetoken;

import java.lang.reflect.Type;

public class Main {

    public static void main(String[] args) {
        final Type type = new TypeReference<Response<Person>>() {
        }.getType();
        System.out.println(type);
    }
}
