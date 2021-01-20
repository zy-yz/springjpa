package com.peppers.exam.utils.design.singleton;

/**
 * @author peppers
 * @description 登记式/静态内部类
 * 这种方式能达到双检锁方式一样的功效，但实现更简单
 * 这种方式只适用于静态域的情况，双检锁方式可在实例域需要延迟初始化时使用
 * @since 2021/1/20
 **/
public class HoldSingleton {

    private static class SingletonHolder{
        private static final HoldSingleton INSTANCE = new HoldSingleton();
    }
    private HoldSingleton(){}
    public static final HoldSingleton getInstance(){
        return SingletonHolder.INSTANCE;
    }
}
