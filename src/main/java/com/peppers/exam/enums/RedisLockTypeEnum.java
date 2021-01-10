package com.peppers.exam.enums;

/**
 * @author zhaoyu
 * redis锁的枚举类
 * 业务属性枚举设定
 */

public enum RedisLockTypeEnum {
    /**
     * 自定义 key 前缀
     */
    ONE("Business1", "Test1"),

    TWO("Business2", "Test2");
    private String code;
    private String desc;
    RedisLockTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    public String getCode() {
        return code;
    }
    public String getDesc() {
        return desc;
    }
    public String getUniqueKey(String key) {
        return String.format("%s:%s", this.getCode(), key);
    }
}
