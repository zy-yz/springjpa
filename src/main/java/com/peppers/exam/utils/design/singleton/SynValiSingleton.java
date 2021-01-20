package com.peppers.exam.utils.design.singleton;

/**
 * @author peppers
 * @description 双重锁机制
 * 这种方式采用双锁机制，安全且在多线程情况下能保持高性能。
 * getInstance() 的性能对应用程序很关键。
 * @since 2021/1/20
 **/
public class SynValiSingleton {
    private static volatile SynValiSingleton instance;

    private SynValiSingleton() {
    }

    public static SynValiSingleton getInstance() {
        if (instance == null) {
            synchronized (SynValiSingleton.class) {
                if (instance == null) {
                    instance = new SynValiSingleton();
                }
            }
        }
        return instance;
    }
}
