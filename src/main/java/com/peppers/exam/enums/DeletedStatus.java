package com.peppers.exam.enums;

/**
 * @author peppers
 * @description
 * @since 2020/12/20
 **/
public enum DeletedStatus {
    FALSE(0, "未删除"),
    TRUE(1, "已删除");

    private final int code;
    private final String name;

    private DeletedStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return this.code;
    }

    public String getName() {
        return this.name;
    }
}
