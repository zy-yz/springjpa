package com.peppers.exam.utils.lambda;

import org.junit.Test;

import java.io.Serializable;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author peppers
 * @description lambda多功能使用
 * @since 2021/1/18
 **/
public class LambdaCreateTest {

    /**创建流的五种方式
     *创建流一共有五种方式
     * 1、通过stream方法将list或者数组转换成流
     * 2、通过stream.of方法直接传入多个元素构成一个流
     * 3、通过stream.iterate方法使用迭代的方式构造一个无限流，然后使用limit限制流元素个数
     * 4、通过stream.generate方法从外部传入一个提供元素的Suppiler来构造无限流，然后使用limit来限制流元素个数
     * 5、通过IntStream或者DoubleStream构造基本类型的流
     *
     * */
    /**通过stream方法将List或者数组转换成流*/
    @Test
    public void listToStream(){
        Arrays.asList("1","2","3").stream().forEach(System.out::println);
        Arrays.stream(new int[]{1,2,3}).forEach(System.out::println);
    }

    /**通过stream.of方法直接传入多个元素构成一个流*/
    @Test
    public void ofToStream(){
        String[] arr = {"1","2","c"};
        Stream.of(arr).forEach(System.out::println);
        Stream.of(1,2,"1").map(item->((Serializable) item).getClass().getName()).forEach(System.out::println);
    }

    /**通过Stream.iterate方法使用迭代的方式构造一个无限流，然后使用limit限制元素个数*/
    @Test
    public void iterateToStream(){
        Stream.iterate(2,item->item *2).limit(10).forEach(System.out::println);
    }

    /**通过Stream.generate方法从外部传入一个提供元素的Supplier来构造无限流，然后使用limit限制流元素个数*/
    @Test
    public void generate(){
        Stream.generate(() -> "test").limit(3).forEach(System.out::println);
        Stream.generate(Math::random).limit(10).forEach(System.out::println);
    }


    /***通过IntStream或DoubleStream构造基本类型的流*/
    @Test
    public void primitive()
    {
        //演示IntStream和DoubleStream
        IntStream.range(1, 3).forEach(System.out::println);
        IntStream.range(0, 3).mapToObj(i -> "x").forEach(System.out::println);

        IntStream.rangeClosed(1, 3).forEach(System.out::println);
        DoubleStream.of(1.1, 2.2, 3.3).forEach(System.out::println);

        //各种转换，后面注释代表了输出结果
        System.out.println(IntStream.of(1, 2).toArray().getClass());
        System.out.println(Stream.of(1, 2).mapToInt(Integer::intValue).toArray().getClass());
        System.out.println(IntStream.of(1, 2).boxed().toArray().getClass());
        System.out.println(IntStream.of(1, 2).asDoubleStream().toArray().getClass());
        System.out.println(IntStream.of(1, 2).asLongStream().toArray().getClass());

        //注意基本类型流和装箱后的流的区别
        Arrays.asList("a", "b", "c").stream()   // Stream<String>
                .mapToInt(String::length)       // IntStream
                .asLongStream()                 // LongStream
                .mapToDouble(x -> x / 10.0)     // DoubleStream
                .boxed()                        // Stream<Double>
                .mapToLong(x -> 1L)             // LongStream
                .mapToObj(x -> "")              // Stream<String>
                .collect(Collectors.toList());
    }


}
