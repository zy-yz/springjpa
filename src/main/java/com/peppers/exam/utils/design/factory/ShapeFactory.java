package com.peppers.exam.utils.design.factory;

/**
 * @author peppers
 * @description
 * @since 2021/1/20
 **/
public class ShapeFactory {

    /**使用 getShape 方法获取形状类型的对象*/
    public Shape getShape(String shapeType){
        if(shapeType == null){
            return null;
        }
        if(shapeType.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if("RECTANGLE".equalsIgnoreCase(shapeType)){
            return new Rectangle();
        } else if(shapeType.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
