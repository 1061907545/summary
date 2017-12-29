package com.expection;

public class RunExpection extends RuntimeException{

	private static final long serialVersionUID = -8999983123041386042L;

    public RunExpection() {
        this("服务器错误");
    }

    public RunExpection(String message) {
        super(message);
    }
}
