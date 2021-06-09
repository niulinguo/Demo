package com.lingo.demo.typetoken;

public class Response<D> {
    private final int code;
    private final String msg;
    private final D data;

    public Response(int code, String msg, D data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
