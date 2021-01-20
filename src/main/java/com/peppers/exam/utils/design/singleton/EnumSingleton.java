package com.peppers.exam.utils.design.singleton;

/**
 * @author peppers
 * @description 实现单例模式的最佳方法
 * 更简洁，自动支持序列化机制，绝对防止多次实例化
 * @since 2021/1/20
 **/
public enum EnumSingleton {
    INSTANCE;
    public void whateverMethod() {
    }
}
