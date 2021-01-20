package com.peppers.exam.utils.design.factory;

/**
 * @author peppers
 * @description
 * @since 2021/1/20
 **/
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
