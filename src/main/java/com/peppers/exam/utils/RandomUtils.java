package com.peppers.exam.utils;

import org.apache.commons.lang3.RandomStringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author peppers
 * @description 生成随机数的几种方法
 * @since 2021/1/4
 **/
public class RandomUtils {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        /**
         *  Math.random() 静态方法
         * */
        //产生的随机数是 0 - 1 之间的一个 double，即 0 <= random <= 1
        for (int i = 0; i < 10; i++) {
            System.out.println(Math.random());
        }

        /**
         * java.util.Random 工具类
         * */
        //生成器缺点：可预测
        //Random类默认使用当前系统时钟作为种子
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            System.out.println(random.nextInt());
        }

        //只要种子一样，产生的随机数也一样： 因为种子确定，随机数算法也确定，因此输出是确定的
        Random random1 = new Random(10000);
        Random random2 = new Random(10000);

        for (int i = 0; i < 5; i++) {
            System.out.println(random1.nextInt() + " = " + random2.nextInt());
        }

        /**
         * java.util.concurrent.ThreadLocalRandom 工具类
         * */
        //继承至 java.util.Random
        //每一个线程有一个独立的随机数生成器 ，用于并发产生随机数，能够解决多个线程发生的竞争争夺。效率更高
        new MyThread().start();
        new MyThread().start();


        /**
         * java.Security.SecureRandom
         * */
        //继承至 java.util.Random
        //SecureRandom 提供加密的强随机数生成器 (RNG)，要求种子必须是不可预知 的，产生非确定性 输出。
        // SecureRandom 也提供了与实现无关的算法，因此，调用方（应用程序代码）会请求特定的 RNG 算法并将它传回到该算法的 SecureRandom 对象中
        SecureRandom randomS1 = SecureRandom.getInstance("SHA1PRNG");
        SecureRandom randomS2 = SecureRandom.getInstance("SHA1PRNG");

        for (int i = 0; i < 5; i++) {
            System.out.println(randomS1.nextInt() + " != " + randomS2.nextInt());
        }

        /**
         * 随机字符串
         * 可以使用 Apache Commons-Lang 包中的 RandomStringUtils 类
         * */
        // Creates a 64 chars length random string of number.
        String result = RandomStringUtils.random(64, false, true);
        System.out.println("random = " + result);

        // Creates a 64 chars length of random alphabetic string.
        result = RandomStringUtils.randomAlphabetic(64);
        System.out.println("random = " + result);

        // Creates a 32 chars length of random ascii string.
        result = RandomStringUtils.randomAscii(32);
        System.out.println("random = " + result);

        // Creates a 32 chars length of string from the defined array of
        // characters including numeric and alphabetic characters.
        result = RandomStringUtils.random(32, 0, 20, true, true, "qw32rfHIJk9iQ8Ud7h0X".toCharArray());
        System.out.println("random = " + result);
    }


    static class  MyThread extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 2; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + ThreadLocalRandom.current().nextDouble());
            }
        }
    }


}
