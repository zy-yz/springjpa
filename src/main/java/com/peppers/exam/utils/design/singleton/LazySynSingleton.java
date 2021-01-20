package com.peppers.exam.utils.design.singleton;

import lombok.Synchronized;

/**
 * @author peppers
 * @description 懒汉式，线程安全
 * 优点：第一次调用才初始化，避免内存浪费。
 * 缺点：必须加锁 synchronized 才能保证单例，但加锁会影响效率。
 * @since 2021/1/20
 **/
public class LazySynSingleton {

    private static LazySynSingleton instance;

    private LazySynSingleton(){};

    public static synchronized LazySynSingleton getInstance(){
        if (instance == null){
            return new LazySynSingleton();
        }
        return instance;

    }
}
