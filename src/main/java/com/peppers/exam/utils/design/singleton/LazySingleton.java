package com.peppers.exam.utils.design.singleton;

/**
 * @author peppers
 * @description 懒汉式单例模式
 *
 * 不支持多线程
 * @since 2021/1/20
 **/
public class LazySingleton {

    private static LazySingleton instance;

    private LazySingleton(){}

    public static LazySingleton getInstance(){
        if (instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }

}
