package com.peppers.exam.utils.design.factory;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author peppers
 * @description
 * @since 2021/1/20
 **/
public class Circle implements Shape {

    @Override
    public void draw() {

        System.out.println("Inside Circle::draw() method.");
    }


}

