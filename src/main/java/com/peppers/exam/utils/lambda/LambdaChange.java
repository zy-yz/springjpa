package com.peppers.exam.utils.lambda;

import com.peppers.exam.entity.menu.SysMenuRole;
import com.peppers.exam.entity.role.SysRole;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * @author peppers
 * @description lambda表达式和之前语法的对比
 *
 * 加了一个参数，在()中间我们加了一个 x ，代表的意思其实是：通过 forEach 方法，我们把一个元素已经赋值到 x 中了，拿到这个 x ，我们就可以输出结果。
 * lambda 的使用方式其实很简单，可以总结为下面的方法。
 * ([参数可选，...]) -> {
 * }
 * @since 2021/1/10
 **/
public class LambdaChange {

    public static void main(String[] args) {
//        //线程的区别
//        ThreadTest();
//        //遍历方式的选择
//        ForTest();
//        //创建流
//        testCreateStream();
//
//        List<Double> list = DoubleStream.of(1.0, 2.0, 3.0)
//                .collect(ArrayList<Double>::new, ArrayList::add, ArrayList::addAll);
//        list.stream().forEach(System.out::println);
//        testStringToStream();
//        testJoinStream();
        testReduce1();

    }


    /**
     * 两种线程的使用方法
     * */
    private static void ThreadTest(){
        //使用匿名内部类的方式启动多线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("这是使用内部类的方式");
            }
        }).start();

        //使用lambda的方式
        new Thread(()->{
            System.out.println("这是使用lambda的方式");
        }).start();
    }

    /**
     * 遍历方式
     * */
    private static void ForTest(){
        //foreach使用
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        for (int i:list){
            System.out.println(i);
        }

        //使用lambda方式代替foreach循环
        Stream.of(1,2,3,4,5).forEach((x)->{
            System.out.println(x);
        });

    }

    /**
     * 流的创建方法
     * */
    public static void testCreateStream(){
        //利用Stream.of方法创建流
        Stream<String> stream = Stream.of("hello","world","Java8");
        stream.forEach(System.out::println);
        System.out.println("----------------");
        //利用Stream.iterate方法创建流
        Stream.iterate(10,n->n+1)
                .limit(5)
                .collect(Collectors.toList())
                .forEach(System.out::println);
        System.out.println("---------------");
        //利用Stream.generate方法创建流
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
        System.out.println("--------------");
        //从现有集合创建流
        List<String> strings = Arrays.asList("hello","world","java");
        String collect = strings.stream().collect(Collectors.joining(","));
        System.out.println(collect);
    }

    /**
     * 字符创转换成流
     * */
    private static void testStringToStream(){
        /**转换成流*/
        String s = "hello world java8".codePoints()
                .collect(StringBuffer::new,
                        StringBuffer::appendCodePoint,
                        StringBuffer::append)
                /**将流转换成字符串*/
                .toString();
        System.out.println(s);

        /**转换成流*/
        String s1 = "hello world Java8".chars()
                .collect(StringBuffer::new,
                        StringBuffer::appendCodePoint,
                        StringBuffer::append)
                /**将流转换成字符串*/
                .toString();
        System.out.println(s1);
    }

    public static void testMapAndFlatMap(){
        List<SysRole> list = new ArrayList<>();
        List<SysMenuRole> menuRoles = new ArrayList<>();
        menuRoles.add(new SysMenuRole());
        SysRole sysRole = new SysRole();
        sysRole.setRoleRelation(menuRoles);
        list.add(sysRole);
        //映射名字
        List<String> collect = list.stream().map(SysRole::getNameEn).collect(Collectors.toList());

//         list.stream().flatMap(m -> m.getRoleRelation().stream().collect(Collectors.toList()));
    }

    public static void testJoinStream(){
        //两个流的连接
        Stream<String> first = Stream.of("1", "2", "3");
        Stream<String> second = Stream.of("4", "5", "6");
        Stream<String> third = Stream.of("7", "8", "9");
//        Stream<String> concat = Stream.concat(first, second);
//        System.out.println(concat.collect(Collectors.joining(",")));

        //多个流的连接
        Stream<String> stringStream = Stream.of(first, second, third).flatMap(Function.identity());
        System.out.println(stringStream.collect(Collectors.joining(",")));
    }

    /**
     *流的规约操作
     **/
    public static void testReduce1() {
        String[] strings = {"hello", "sihai", "hello", "Java8"};
        long count = Arrays.stream(strings) .map(String::length) .count();
        System.out.println(count);
        System.out.println("##################");
        int sum = Arrays.stream(strings) .mapToInt(String::length) .sum(); System.out.println(sum);
        System.out.println("##################");
        OptionalDouble average = Arrays.stream(strings) .mapToInt(String::length) .average();
        System.out.println(average); System.out.println("##################");
        OptionalInt max = Arrays.stream(strings) .mapToInt(String::length) .max();
        System.out.println(max); System.out.println("##################");
        OptionalInt min = Arrays.stream(strings) .mapToInt(String::length) .min();
        System.out.println(min);
        DoubleSummaryStatistics statistics = DoubleStream.generate(Math::random) .limit(1000) .summaryStatistics();
        System.out.println(statistics);
    }

    /**
     * 流的计数
     * */
    public static void testStatistics() {
        //统计数量
        String[] strings = {"hello", "sihai", "hello", "Java8"};
        long count = Arrays.stream(strings)
                .count();
        System.out.println(count);

        System.out.println("##################");

        Long count2 = Arrays.stream(strings)
                .collect(Collectors.counting());
        System.out.println(count2);

    }

    /**
     * 流的查找
     * */
    public static void testFind(){
        String[] strings = {"hello", "sihai", "hello", "Java8"};
        Optional<String> first = Arrays.stream(strings)
                .findFirst();
        System.out.println(first.get());

        System.out.println("##################");

        Optional<String> any = Arrays.stream(strings).findAny();
        System.out.println(any.get());

        System.out.println("##################");
    }

    /**
     * 流的匹配
     * */
    public static void testMatch(){
        boolean b = Stream.of(1, 2, 3, 4, 5, 10)
                .anyMatch(x -> x > 5);
        System.out.println(b);

        System.out.println("##################");

        boolean b2 = Stream.of(1, 2, 3, 4, 5, 10)
                .allMatch(x -> x > 5);
        System.out.println(b2);

        System.out.println("##################");

        boolean b3 = Stream.of(1, 2, 3, 4, 5, 10)
                .noneMatch(x -> x > 5);
        System.out.println(b3);
    }


}
