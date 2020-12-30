package com.peppers.exam.config.webmvcconfig;



import java.lang.annotation.*;

/**
 * @author peppers
 * @description  自定义一个注解对返回结果进行包装
 * @since 2020/12/30
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WarpWithData {
}
