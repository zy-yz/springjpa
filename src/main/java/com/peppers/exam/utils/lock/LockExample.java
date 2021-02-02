package com.peppers.exam.utils.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author peppers
 * @description 死锁
 * @since 2021/2/2
 **/
public class LockExample {

    public static void main(String[] args) {
        deadLock(); // 死锁

        reentrantA();

        synchronized (LockExample.class) {
            System.out.println("lock");
        }
    }


    /**
     * 死锁
     * */
    private static void deadLock(){
        Object lock1 = new Object();
        Object lock2 = new Object();
        //线程一拥有lock1试图获取lock2
        new Thread(()->{
            synchronized (lock1){
                System.out.println("获取lock1成功");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //试图获取锁lock2
                synchronized (lock2){
                    System.out.println(Thread.currentThread().getName());
                }

            }
        }).start();
        //线程2拥有lock2试图获取lock
        new Thread(()->{
            synchronized (lock2){
                System.out.println("获取lock2成功");
            }try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //试图获取锁lock1
            synchronized (lock1){
                System.out.println(Thread.currentThread().getName());
            }

        }).start();
    }

    /**
     * 可重入锁 A 方法
     */
    private synchronized static void reentrantA() {
        System.out.println(Thread.currentThread().getName() + "：执行 reentrantA");
        reentrantB();
    }
    /**
     * 可重入锁 B 方法
     */
    private synchronized static void reentrantB() {
        System.out.println(Thread.currentThread().getName() + "：执行 reentrantB");
    }
}
