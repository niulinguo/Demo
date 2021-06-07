package com.lingo.demo;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class TypeReference<T> {

    private final Type type;

    public TypeReference() {
        final Type superclass = getClass().getGenericSuperclass();
        final ParameterizedType parameterizedType = (ParameterizedType) superclass;
        final Type[] typeArguments = parameterizedType.getActualTypeArguments();
        type = typeArguments[0];
    }

    public Type getType() {
        return type;
    }
}
